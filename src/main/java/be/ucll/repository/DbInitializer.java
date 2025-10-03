package be.ucll.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ucll.model.Antwoorden;
import be.ucll.model.User;
import be.ucll.model.Vragen;
import jakarta.annotation.PostConstruct;

@Component
public class DbInitializer {

    private UserRepository userRepository;
    private VragenRepository vragenRepository;
    private AntwoordenRepository antwoordenRepository;

    @Autowired
    public DbInitializer(UserRepository userRepository, VragenRepository vragenRepository,
     AntwoordenRepository antwoordenRepository) {
        this.userRepository = userRepository;
        this.vragenRepository = vragenRepository;
        this.antwoordenRepository = antwoordenRepository;
    }

    @PostConstruct
    public void initialize() {


       


            // Vragen vraag3 = new Vragen("1ste vraag","Wat is je naam?", List.of(new Antwoorden(true, "Souleyman")));


            // vragenRepository.save(vraag3);

            

            // Create answers
            Antwoorden a1 = new Antwoorden(true, "Souleyman");

            // Create question and link answers
            Vragen vraag3 = new Vragen("1ste vraag", "Wat is je naam?", List.of(a1));

            // Set the parent in the child
            a1.setVragen(vraag3);

            // Save the question (cascade will save answers)
            vragenRepository.save(vraag3);



    
    };
}
