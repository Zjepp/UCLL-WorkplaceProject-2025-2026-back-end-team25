
package be.ucll.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Vragen> getAll() {
        return vragenService.getAll();
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/random18")
    public List<Vragen> get18RandomQuestions() {
        return vragenService.get18RandomQuestions();
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/random8")
    public List<Vragen> get8RandomConnectQuestions() {
        return vragenService.get8RandomConnectQuestions();
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/category/{category}")
    public List<Vragen> getQuestionsByCategory(@org.springframework.web.bind.annotation.PathVariable String category) {
        return vragenService.getQuestionsByCategory(category);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping
    public Vragen createVraag(@RequestBody Vragen vraag) {
        return vragenService.saveVraag(vraag);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteVraag(@PathVariable Long id) {
        vragenService.deleteVraag(id);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public Vragen getVraag(@PathVariable Long id) {
        return vragenService.getVraagById(id);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping
    public Vragen updateVraag(@RequestBody Vragen vraag) {
        return vragenService.updateVraag(vraag);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Bestand is leeg");
        }
        int added = vragenService.addVragenFromCsv(file);
        if (added == 0) {
            return ResponseEntity.ok("Geen nieuwe vragen toegevoegd.");
        }
        return ResponseEntity.ok(added + " vragen succesvol toegevoegd.");
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return vragenService.getAllCategories();
    }

}
