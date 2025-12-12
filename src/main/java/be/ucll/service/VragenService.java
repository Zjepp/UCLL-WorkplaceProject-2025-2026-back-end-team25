package be.ucll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.ucll.model.Antwoorden;
import be.ucll.model.Vragen;
import be.ucll.repository.AntwoordenRepository;
import be.ucll.repository.VragenRepository;

@Service
public class VragenService {

    private AntwoordenRepository antwoordenRepository;
    private VragenRepository vragenRepository;

    public VragenService(AntwoordenRepository antwoordenRepository, VragenRepository vragenRepository) {
        this.antwoordenRepository = antwoordenRepository;
        this.vragenRepository = vragenRepository;
    }

    public List<Vragen> getAll() {
        return vragenRepository.findAll();
    }

    public List<Vragen> get18RandomQuestions() {
        return vragenRepository.find18RandomQuestions();
    }

    public List<Vragen> get8RandomConnectQuestions() {
        return vragenRepository.find8RandomConnectQuestions();
    }

    public List<Vragen> getQuestionsByCategory(String category) {
        List<Vragen> connectChoiceQuestions = vragenRepository.find8ConnectChoiceQuestionsByCategory(category);
        List<Vragen> otherQuestions = vragenRepository.find18OtherQuestionsByCategory(category);

        connectChoiceQuestions.addAll(otherQuestions);
        return connectChoiceQuestions;
    }

    public Vragen saveVraag(Vragen vraag) {
        return vragenRepository.save(vraag);
    }

    public void deleteVraag(Long id) {
        Vragen vraag = vragenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vraag niet gevonden met id: " + id));
        vragenRepository.deleteById(id);
    }

    public Vragen getVraagById(Long id) {
        return vragenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vraag niet gevonden met id: " + id));
    }

    public Vragen updateVraag(Vragen updatedVraag) {
        Vragen bestaande = getVraagById(updatedVraag.getId());

        // Update velden
        bestaande.setVraag(updatedVraag.getVraag());
        bestaande.setType(updatedVraag.getType());

        // Verwijder oude antwoorden
        antwoordenRepository.deleteAll(bestaande.getAntwoorden());
        bestaande.getAntwoorden().clear();

        // Voeg nieuwe antwoorden toe (met link naar deze vraag)
        if (updatedVraag.getAntwoorden() != null) {
            for (Antwoorden a : updatedVraag.getAntwoorden()) {
                a.setVragen(bestaande); // belangrijk: bidirectionele link
                bestaande.getAntwoorden().add(a);
            }
        }

        return vragenRepository.save(bestaande);
    }

}
