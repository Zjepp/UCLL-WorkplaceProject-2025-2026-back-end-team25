package be.ucll.DTO;

import be.ucll.model.User.Role;

public record LoginResponseDTO(
        String email,
        Role role,
        String username,
        String token) {

}
