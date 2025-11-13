package be.ucll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import be.ucll.DTO.AuthDTO;
import be.ucll.DTO.LoginDTO;
import be.ucll.DTO.LoginResponseDTO;
import be.ucll.model.User;
import be.ucll.model.User.Role;
import be.ucll.repository.UserRepository;
import be.ucll.util.security.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    public LoginResponseDTO authenticate(LoginDTO authDTO) throws Exception {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password()));
            System.out.println("User Out:"+userRepository.findByEmail(authDTO.email()));

            User user = userRepository.findByEmail(authDTO.email())
                    .orElseThrow(() -> new ServiceException("User with email does not exist", "email"));
            String username = user.getUsername();
            String token = jwtUtils.generateJwtToken(user.getEmail());

            return new LoginResponseDTO(user.getEmail(), user.getRole(), username, token);
    }

    public void authorize(Role role, Role requiredRole) throws Exception {
        if (role != requiredRole) {
            throw new Exception("Acces denied: User does not have required role: " + requiredRole.toString());
        }
    }
}
