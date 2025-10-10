import be.ucll.model.User;

public class UserTest {
    // Test cases for User model
    @Test
    public void testUserCreation() {
        User user = new User("Jane Doe", 25, "jane.doe@example.com", "janedoe", 0, "securepassword", "USER", new ArrayList<>(), null);
        assertNotNull(user);
        assertEquals("Jane Doe", user.getName());
        assertEquals(25, user.getAge());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("janedoe", user.getUsername());
        assertEquals(0, user.getScore());
        assertEquals("securepassword", user.getPassword());
        assertEquals("USER", user.getRole());
    }
}
