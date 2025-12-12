package be.ucll.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    public User addUser(User userData) {
        Optional<User> existingUser = userRepository.findByEmail(userData.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User already exists.");
        }
        userRepository.save(userData);
        return userData;
    }

    public int addUsersFromCsv(MultipartFile file) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                } // skip header

                String[] parts = line.split(",");
                if (parts.length < 3)
                    continue;

                String fullName = parts[0].trim();
                String username = parts[1].trim();
                String email = parts[2].trim();

                // Fix lege username (gebeurt bij sommige rijen in jouw csv)
                if (username.isEmpty() || username.equals(fullName.toLowerCase().replace(" ", ""))) {
                    username = email.substring(0, email.indexOf("@")).replace(".", "");
                }

                // Als email leeg is, gebruik de 3e kolom (jouw csv is soms verschoven)
                if (email.isEmpty() && parts.length > 2) {
                    email = parts[2].trim();
                }

                // Skip als email ongeldig of al bestaat
                if (email.isEmpty() || email.equals("E-mailadres (ongeacht weergavevoorkeur)"))
                    continue;
                if (userRepository.findByEmail(email).isPresent())
                    continue;

                // Maak user aan precies zoals jij vroeg
                User newUser = new User(fullName, email, username, 0, username, User.Role.ADMIN, null);

                userRepository.save(newUser);
                count++;
            }
        } catch (Exception e) {
            throw new RuntimeException("Fout bij inlezen CSV: " + e.getMessage());
        }
        return count;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
