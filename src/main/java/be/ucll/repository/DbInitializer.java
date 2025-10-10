package be.ucll.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import be.ucll.model.Antwoorden;
import be.ucll.model.Resultaat;
import be.ucll.model.User;
import be.ucll.model.Vragen;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {

    private UserRepository userRepository;
    private VragenRepository vragenRepository;
    private AntwoordenRepository antwoordenRepository;
    private ResultaatRepository resultaatRepository;

    @Autowired
    public DbInitializer(UserRepository userRepository, VragenRepository vragenRepository,
     AntwoordenRepository antwoordenRepository, ResultaatRepository resultaatRepository) {
        this.userRepository = userRepository;
        this.vragenRepository = vragenRepository;
        this.antwoordenRepository = antwoordenRepository;
        this.resultaatRepository = resultaatRepository;
    }

    @PostConstruct
    public void initialize() {
        // Create the answer and question
        Antwoorden a1 = new Antwoorden(true, "Souleyman");
        Vragen vraag3 = new Vragen("1ste vraag", "Wat is je naam?", List.of(a1), null);
        a1.setVragen(vraag3);

        // Save the question first (this cascades Antwoorden)
        vragenRepository.save(vraag3);

        // Now create user and result (linking them after question exists)
        User user1 = new User("Henk", "henk@hotmail.com", "henkie", 0, "henk123", "user", null);
        userRepository.save(user1); // persist user to get an ID

        Resultaat r1 = new Resultaat("Souleyman");
        r1.setUser(user1);
        r1.setVraag(vraag3);

        // Save resultaat (this will link user + vraag)
        resultaatRepository.save(r1);
    };
}
