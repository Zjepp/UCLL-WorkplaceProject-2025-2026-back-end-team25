package be.ucll.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    // Test cases for User model
    // @Test
    // public void testUserCreation() {
    //     User user = new User("Jane Doe", "jane.doe@example.com", "janedoe", 0, "securepassword", "USER", new ArrayList<>());
    //     assertNotNull(user);
    //     assertEquals("Jane Doe", user.getName());
    //     assertEquals("jane.doe@example.com", user.getEmail());
    //     assertEquals("janedoe", user.getUsername());
    //     assertEquals(0, user.getScore());
    //     assertEquals("securepassword", user.getPassword());
    //     assertEquals("USER", user.getRole());
    // }

    // @Test
    // public void testUserCreationWithEmptyFields() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         new User("", "", "", 0, "", "", new ArrayList<>());
    //     });

    //     String expectedMessage = "Name cannot be empty";
    //     String actualMessage = exception.getMessage();
    //     assertTrue(actualMessage.contains(expectedMessage));
    // }

    // @Test
    // public void testUserCreationNoName() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         new User("", "jane.doe@example.com", "janedoe", 0, "securepassword", "USER", new ArrayList<>());
    //     });

    //     String expectedMessage = "Name cannot be empty";
    //     String actualMessage = exception.getMessage();
    //     assertTrue(actualMessage.contains(expectedMessage));
    // }

    // @Test
    // public void testUserCreationNoEmail() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         new User("Jane Doe", "", "janedoe", 0, "securepassword", "USER", new ArrayList<>());
    //     });

    //     String expectedMessage = "Email cannot be empty";
    //     String actualMessage = exception.getMessage();
    //     assertTrue(actualMessage.contains(expectedMessage));
    // }

    // @Test
    // public void testUserCreationNoUsername() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         new User("Jane Doe", "jane.doe@example.com", "", 0, "securepassword", "USER", new ArrayList<>());
    //     });

    //     String expectedMessage = "Username cannot be empty";
    //     String actualMessage = exception.getMessage();
    //     assertTrue(actualMessage.contains(expectedMessage));
    // }

    // // @Test
    // // public void testUserCreationNoPassword() {
    // //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    // //         new User("Jane Doe", "jane.doe@example.com", "janedoe", 0, "", "USER", new ArrayList<>());
    // //     });

    // //     String expectedMessage = "Password cannot be empty";
    // //     String actualMessage = exception.getMessage();
    // //     assertTrue(actualMessage.contains(expectedMessage));
    // // }
}