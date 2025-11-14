package be.ucll.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ucll.model.Resultaat;
import be.ucll.service.ResultaatService;

@RestController
@RequestMapping("/resultaat")
public class ResultaatRestController {

    private ResultaatService resultaatService;

    public ResultaatRestController(ResultaatService resultaatService) {
        this.resultaatService = resultaatService;
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Resultaat> getAll() {
        return resultaatService.getAll();
    }
    
}
