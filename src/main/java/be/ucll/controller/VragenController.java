package be.ucll.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ucll.model.Antwoorden;
import be.ucll.model.Vragen;
import be.ucll.service.AntwoordenService;
import be.ucll.service.VragenService;

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

    @GetMapping("/random20")
    public List<Vragen> get20RandomQuestions() {
        return vragenService.get20RandomQuestions();
    }

    @PostMapping
    public Vragen createVraag(@RequestBody Vragen vraag) {
        return vragenService.saveVraag(vraag);
    }

    @DeleteMapping("/{id}")
    public void deleteVraag(@PathVariable Long id) {
        vragenService.deleteVraag(id);
    }

    @GetMapping("/{id}")
    public Vragen getVraag(@PathVariable Long id) {
        return vragenService.getVraagById(id);
    }

    // 2. Wijzigen van een vraag
    @PutMapping
    public Vragen updateVraag(@RequestBody Vragen vraag) {
        return vragenService.updateVraag(vraag);
    }

}
