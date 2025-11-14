package be.ucll.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ucll.model.Vragen;
import be.ucll.service.AntwoordenService;
import be.ucll.service.VragenService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/vragen")
public class VragenController {

    private VragenService vragenService;
    private AntwoordenService antwoordenService;

    public VragenController(VragenService vragenService, AntwoordenService antwoordenService) {
        this.vragenService = vragenService;
        this.antwoordenService = antwoordenService;
    }


    @GetMapping("/all")
    public List<Vragen> getAll() {
        return vragenService.getAll();
    }


    @GetMapping("/random18")
    public List<Vragen> get18RandomQuestions() {
        return vragenService.get18RandomQuestions();
    }
    
    @GetMapping("/random8")
    public List<Vragen> get8RandomQuestions() {
        return vragenService.get8RandomQuestions();
    }
    
}
