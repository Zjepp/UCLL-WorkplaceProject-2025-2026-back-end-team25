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

    public List<Vragen> get18RandomQuestions() {
        return vragenRepository.find18RandomQuestions();
    }

    public List<Vragen> get8RandomQuestions() {
        return vragenRepository.find8RandomQuestions();
    }
}
