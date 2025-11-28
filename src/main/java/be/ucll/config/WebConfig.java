package be.ucll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://localhost:8081",              // Local Expo dev server
                    "http://localhost:19006",             // Alternative Expo web port
                    "https://*.apps.okd.ucll.cloud",      // OKD frontend HTTPS (wildcard)
                    "http://*.apps.okd.ucll.cloud",       // OKD frontend HTTP (wildcard)
                    "https://front-end-team25-wpp-team-25.apps.okd.ucll.cloud",  // Specific OKD frontend HTTPS
                    "http://front-end-team25-wpp-team-25.apps.okd.ucll.cloud"    // Specific OKD frontend HTTP
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
