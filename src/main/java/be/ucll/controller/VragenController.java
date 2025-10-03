package be.ucll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
