package be.ucll.service;

import org.springframework.stereotype.Service;

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

}
