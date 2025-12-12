package be.ucll.util.security;

import org.springframework.stereotype.Component;

import be.ucll.model.User;
import be.ucll.repository.UserRepository;
import be.ucll.service.ServiceException;
import be.ucll.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy; // <--- VOEG DEZE IMPORT TOE

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtils {

    @Autowired
    @Lazy
    UserService userService;

    @Autowired
    UserRepository userRepository;

    // private static final String secret = System.getenv("JWT_SECRET");
    private static final String secret = "oqisdhjfqdksfjmqdsjfdsfqds6f4qs6d4fq6sd54fq5ds4f3q4sd65fqsd6f4qs6d1f6f5h165gj1j6165m1gfsh651qf5g165i165k165ggqf1dq65g165s1h651qf6g165sg65fg46sdfgs165dfg16dfs";

    private final int jwtExpiration = 86400000; // TODO: DISCUSS EXPIRATION DATE
    private final Key key;

    public JwtUtils() {
        this.key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        if (secret == null || secret.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET environment variable is not set.");
        }
    }

    public String generateJwtToken(String email) throws ServiceException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ServiceException("User with email does not exist", "email"));

        Claims claims = Jwts.claims();
        claims.setSubject(user.getEmail());
        claims.put("role", user.getRole());
        claims.put("user_id", user.getId());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration));

        return Jwts.builder()
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("role", String.class);
    }

    public Long getUserIdFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("user_id", Long.class);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
