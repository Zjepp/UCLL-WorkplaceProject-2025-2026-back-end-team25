package be.ucll.service;

import org.springframework.stereotype.Service;

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
    
}
