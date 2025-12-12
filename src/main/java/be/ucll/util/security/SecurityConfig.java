package be.ucll.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

        private final CustomUserDetailsService userDetailsService;
        private final JwtAuthorizationFilter jwtAuthorizationFilter;

        @Autowired
        public SecurityConfig(CustomUserDetailsService userDetailsService,
                        JwtAuthorizationFilter jwtAuthorizationFilter) {
                this.userDetailsService = userDetailsService;
                this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
                AuthenticationManagerBuilder authenticationManagerBuilder = http
                                .getSharedObject(AuthenticationManagerBuilder.class);
                authenticationManagerBuilder
                                .userDetailsService(userDetailsService)
                                .passwordEncoder(passwordEncoder());
                return authenticationManagerBuilder.build();
        }

        @Bean
        FilterRegistrationBean<MultipartFilter> multipartFilterRegistrationBean() {
                FilterRegistrationBean<MultipartFilter> registration = new FilterRegistrationBean<>();
                registration.setFilter(new MultipartFilter());
                registration.setOrder(Ordered.HIGHEST_PRECEDENCE); // Voor Spring Security
                registration.addUrlPatterns("/*");
                registration.setName("multipartFilter");
                return registration;
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(
                                                                "/auth/register",
                                                                "/auth/checkUserVerified/**",
                                                                "/auth/verifyAccount/**",
                                                                "/auth/sendVerificationEmail",
                                                                "/auth/login",
                                                                "/vragen/**",
                                                                "/auth/**",
                                                                "/faq/**",
                                                                "/h2/**",
                                                                "/h2",
                                                                "/swagger-ui/**",
                                                                "/api-docs/**",
                                                                "/swagger-docs")
                                                .permitAll()
                                                // "/users/**" bewust verwijderd → nu vereist authenticatie
                                                .anyRequest().authenticated())
                                .exceptionHandling(handling -> handling
                                                .authenticationEntryPoint((request, response, authException) -> {
                                                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                                                        "Unauthorized");
                                                }))
                                .headers(headers -> headers
                                                .frameOptions(frameOptions -> frameOptions.sameOrigin()))
                                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}