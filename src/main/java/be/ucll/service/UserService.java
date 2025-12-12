package be.ucll.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import be.ucll.model.User;
import be.ucll.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // <-- injecteren
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

    // ... andere imports

    public int addUsersFromCsv(MultipartFile file) {
        Logger log = LoggerFactory.getLogger(UserService.class);
        int count = 0;
        int lineNumber = 0;

        log.info("Start verwerken CSV: filename={}, size={} bytes, contentType={}",
                file.getOriginalFilename(), file.getSize(), file.getContentType());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    log.debug("Lege lijn {} - skipped", lineNumber);
                    continue;
                }

                if (firstLine) {
                    log.info("Header lijn {}: {}", lineNumber, line);
                    firstLine = false;
                    continue;
                }

                log.debug("Verwerken lijn {}: {}", lineNumber, line);

                String[] parts = line.split(";");
                if (parts.length < 3) {
                    log.warn("Te weinig kolommen in lijn {} ({} parts) - skipped", lineNumber, parts.length);
                    continue;
                }

                String fullName = parts[0].trim();
                String username = parts[1].trim();
                String email = parts[2].trim();

                log.debug("Geparsed - fullName: '{}', username: '{}', email: '{}'", fullName, username, email);

                // Fix lege username
                if (username.isEmpty() || username.equals(fullName.toLowerCase().replace(" ", ""))) {
                    if (email.contains("@")) {
                        username = email.substring(0, email.indexOf("@")).replace(".", "");
                        log.info("Username was leeg/onjuist - nieuwe username gegenereerd: {}", username);
                    }
                }

                // Skip als email ongeldig of header-tekst
                if (email.isEmpty() || email.equals("E-mailadres (ongeacht weergavevoorkeur)")) {
                    log.warn("Ongeldige of lege email in lijn {} - skipped", lineNumber);
                    continue;
                }

                // Check of user al bestaat
                if (userRepository.findByEmail(email).isPresent()) {
                    log.info("User met email {} bestaat al - skipped", email);
                    continue;
                }

                // Maak user aan
                User newUser = new User(fullName, email, username, 0, passwordEncoder.encode(username), User.Role.ADMIN,
                        null);
                userRepository.save(newUser);
                count++;

                log.info("User succesvol aangemaakt (lijn {}): {} <{}> (username: {})", lineNumber, fullName, email,
                        username);
            }

            log.info("CSV verwerking klaar: {} lijnen verwerkt, {} gebruikers aangemaakt", lineNumber, count);

        } catch (Exception e) {
            log.error("Fout bij inlezen CSV: {}", e.getMessage(), e);
            throw new RuntimeException("Fout bij inlezen CSV: " + e.getMessage());
        }

        return count;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
