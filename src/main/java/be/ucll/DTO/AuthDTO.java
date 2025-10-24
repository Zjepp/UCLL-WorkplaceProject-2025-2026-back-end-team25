package be.ucll.DTO;

import be.ucll.model.User.Role;

public record AuthDTO(
        String email,
        String password,
        Role role,
        String token,
        String fullName,
        long id) {

    public AuthDTO(String email, String password) {
        this(email, password, null, null, null, 0);
    }

    public AuthDTO(String email, Role role) {
        this(email, null, role, null, null, 0);
    }

    public AuthDTO(String email, Role role, String fullName, long id) {
        this(email, null, role, null, fullName, id);
    }

    public AuthDTO(String email, Role role, String fullName, String token) {
        this(email, null, role, token, fullName, 0);
    }
}