package be.ucll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.ucll.model.Resultaat;
import be.ucll.repository.ResultaatRepository;

@Service
public class ResultaatService {
    
    private ResultaatRepository resultaatRepository;
    

    public ResultaatService(ResultaatRepository resultaatRepository) {
        this.resultaatRepository = resultaatRepository;
    }


    public List<Resultaat> getAll() {
        return resultaatRepository.findAll();
    }

}
