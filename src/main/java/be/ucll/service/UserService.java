package be.ucll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.model.User;
import be.ucll.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {return user.get();}
        else {
            throw new RuntimeException("User not found.");
        }
    }

    public User addUser(User user) {
        if (user == null) throw new NullPointerException();
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        // Treat null as user not found (safe for legacy/misbehaving repos)
        if (existing != null && existing.isPresent()) {
            throw new RuntimeException("User already exists.");
        }
        User saved = userRepository.save(user);
        if (saved == null) {
            throw new RuntimeException("Failed to save user.");
        }
        return saved;
    }
}
