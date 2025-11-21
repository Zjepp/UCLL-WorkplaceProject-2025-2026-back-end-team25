package be.ucll.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public List<Vragen> get20RandomQuestions() {
        return vragenRepository.find20RandomQuestions();
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

}
