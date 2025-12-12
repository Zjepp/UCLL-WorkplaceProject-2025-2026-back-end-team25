package be.ucll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import be.ucll.model.Resultaat;
import be.ucll.repository.ResultaatRepository;

@Service
public class ResultaatService {
    
    private final ResultaatRepository resultaatRepository;
    
    public ResultaatService(ResultaatRepository resultaatRepository) {
        this.resultaatRepository = resultaatRepository;
    }

    public List<Resultaat> getAll() {
        return resultaatRepository.findAll();
    }

    public Resultaat voegResultaatToe(Resultaat resultaat) {
        if (resultaat == null) {
            throw new IllegalArgumentException("Resultaat mag niet null zijn");
        }
        return resultaatRepository.save(resultaat);
    }

    public Resultaat getResultaatById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id mag niet null zijn");
        }
        return resultaatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultaat niet gevonden met id: " + id));
    }
}
