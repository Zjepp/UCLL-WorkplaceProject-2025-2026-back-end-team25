package be.ucll.service;

import be.ucll.model.User;
import be.ucll.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUsername("henkie");
        user.setName("Henk");
        user.setEmail("henk@hotmail.com");
    }

    @Test
    void testAddUser_Success() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User saved = userService.addUser(user);

        assertNotNull(saved);
        assertEquals("henkie", saved.getUsername());
        assertEquals("Henk", saved.getName());
        assertEquals("henk@hotmail.com", saved.getEmail());
    }

    @Test
    void testAddUser_AlreadyExists() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.addUser(user));
        assertEquals("User already exists.", ex.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAddUser_NullUser_ThrowsException() {
        assertThrows(NullPointerException.class, () -> userService.addUser(null));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAddUser_EmptyEmail() {
        user.setEmail("");
        when(userRepository.findByEmail("")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User saved = userService.addUser(user);

        assertNotNull(saved);
        assertEquals("", saved.getEmail());
    }

    @Test
    void testAddUser_RepositoryReturnsNullOptional() {
        // Simulate repository returning null instead of Optional
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.addUser(user));
        assertEquals("Failed to save user.", ex.getMessage());
    }

    // Additional tests

    @Test
    void testAddUser_WithNullEmail_ThrowsException() {
        user.setEmail(null);
        when(userRepository.findByEmail(null)).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User saved = userService.addUser(user);

        assertNotNull(saved);
        assertNull(saved.getEmail());
    }

    @Test
    void testAddUser_WithExistingEmailDifferentUser_ThrowsException() {
        User anotherUser = new User();
        anotherUser.setUsername("janedoe");
        anotherUser.setName("Jane");
        anotherUser.setEmail("henk@hotmail.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(anotherUser));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.addUser(user));
        assertEquals("User already exists.", ex.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testAddUser_SaveReturnsNull() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.addUser(user));
        assertEquals("Failed to save user.", ex.getMessage());
    }

    @Test
    void testAddUser_SaveThrowsException() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class, () -> userService.addUser(user));
    }

    @Test
    void testAddUser_EmailWhitespace() {
        user.setEmail("   ");
        when(userRepository.findByEmail("   ")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User saved = userService.addUser(user);

        assertNotNull(saved);
        assertEquals("   ", saved.getEmail());
    }

    @Test
    void testGetUserByEmail_Success() {
        when(userRepository.findByEmail("henk@hotmail.com")).thenReturn(Optional.of(user));

        User found = userService.getUserByEmail("henk@hotmail.com");

        assertNotNull(found);
        assertEquals("henkie", found.getUsername());
        assertEquals("Henk", found.getName());
    }

    @Test
    void testGetUserByEmail_NotFound() {
        when(userRepository.findByEmail("unknown@mail.com")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getUserByEmail("unknown@mail.com"));
    }

    @Test
    void testGetAllUsers() {
        User user2 = new User();
        user2.setUsername("janedoe");
        user2.setName("Jane");
        user2.setEmail("jane@hotmail.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user, user2));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertTrue(users.contains(user));
        assertTrue(users.contains(user2));
    }

    @Test
    void testGetAllUsers_EmptyList() {
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertTrue(users.isEmpty());
    }

    @Test
    void testGetUserByEmail_NullEmail_ThrowsException() {
        assertThrows(RuntimeException.class, () -> userService.getUserByEmail(null));
    }

    @Test
    void testGetUserByEmail_EmptyEmail_ThrowsException() {
        when(userRepository.findByEmail("")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.getUserByEmail(""));
    }
}