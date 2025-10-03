package be.ucll.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
