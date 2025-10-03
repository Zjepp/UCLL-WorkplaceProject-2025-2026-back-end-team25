package be.ucll.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ucll.model.User;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {

    private UserRepository userRepository;

    @Autowired
    public DbInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initialize() {

    }
}
