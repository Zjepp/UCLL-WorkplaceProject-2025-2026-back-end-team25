package be.ucll.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    // Test cases for User model
    @Test
    public void testUserCreation() {
        User user = new User("Jane Doe", "jane.doe@example.com", "janedoe", 0, "securepassword", "USER", new ArrayList<>());
        assertNotNull(user);
        assertEquals("Jane Doe", user.getName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("janedoe", user.getUsername());
        assertEquals(0, user.getScore());
        assertEquals("securepassword", user.getPassword());
        assertEquals("USER", user.getRole());
    }
}

