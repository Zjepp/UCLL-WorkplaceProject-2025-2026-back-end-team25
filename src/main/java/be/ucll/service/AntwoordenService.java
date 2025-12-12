package be.ucll.service;

import org.springframework.stereotype.Service;

import be.ucll.model.Antwoorden;
import be.ucll.repository.AntwoordenRepository;
import be.ucll.repository.VragenRepository;


@Service
public class AntwoordenService {


    private AntwoordenRepository antwoordenRepository;
    private VragenRepository vragenRepository;
    

    public AntwoordenService(AntwoordenRepository antwoordenRepository, VragenRepository vragenRepository) {
        this.antwoordenRepository = antwoordenRepository;
        this.vragenRepository = vragenRepository;
    }
    
    public Antwoorden voegAntwoordToe(Antwoorden antwoord) {
        if (antwoord == null || antwoord.getAntwoord() == null || antwoord.getAntwoord().trim().isEmpty()) {
            throw new IllegalArgumentException("Antwoord mag niet leeg zijn");
        }
        return antwoordenRepository.save(antwoord);
    }

    public Antwoorden getAntwoordById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id mag niet null zijn");
        }
        return antwoordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Antwoord niet gevonden met id: " + id));
    }
}
