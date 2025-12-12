package be.ucll.service;

import be.ucll.DTO.LoginDTO;
import be.ucll.DTO.LoginResponseDTO;
import be.ucll.model.User;
import be.ucll.model.User.Role;
import be.ucll.repository.UserRepository;
import be.ucll.util.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
	@Mock
	private UserService userService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private PasswordEncoder passwordEncoder;
	@Mock
	private AuthenticationManager authenticationManager;
	@Mock
	private JwtUtils jwtUtils;

	@InjectMocks
	private AuthService authService;

	private User user;
	private LoginDTO loginDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		user = new User();
		user.setUsername("henkie");
		user.setName("Henk");
		user.setEmail("henk@hotmail.com");
		user.setRole(Role.USER);
		loginDTO = new LoginDTO("henk@hotmail.com", "password");
	}

	@Test
	void testAuthenticate_Success() throws Exception {
		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
		when(jwtUtils.generateJwtToken(user.getEmail())).thenReturn("token123");

		LoginResponseDTO response = authService.authenticate(loginDTO);

		assertNotNull(response);
		assertEquals(user.getEmail(), response.email());
		assertEquals(user.getRole(), response.role());
		assertEquals(user.getUsername(), response.username());
		assertEquals("token123", response.token());
	}

	@Test
	void testAuthenticate_UserNotFound() {
		when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

		ServiceException ex = assertThrows(ServiceException.class, () -> authService.authenticate(loginDTO));
		assertEquals("User with email does not exist", ex.getMessage());
		assertEquals("email", ex.getField());
	}

	@Test
	void testAuthorize_Success() throws Exception {
		authService.authorize(Role.ADMIN, Role.ADMIN);
	}

	@Test
	void testAuthorize_Fail() {
		Exception ex = assertThrows(Exception.class, () -> authService.authorize(Role.USER, Role.ADMIN));
		assertEquals("Acces denied: User does not have required role: ADMIN", ex.getMessage());
	}
}
