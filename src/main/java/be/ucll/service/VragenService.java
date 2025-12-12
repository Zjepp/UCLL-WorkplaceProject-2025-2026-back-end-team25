package be.ucll.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import be.ucll.model.Antwoorden;
import be.ucll.model.User;
import be.ucll.model.Vragen;
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

    public List<Vragen> getAll() {
        return vragenRepository.findAll();
    }

    public List<Vragen> get18RandomQuestions() {
        return vragenRepository.find18RandomQuestions();
    }

    public List<Vragen> get8RandomConnectQuestions() {
        return vragenRepository.find8RandomConnectQuestions();
    }

    public List<Vragen> getQuestionsByCategory(String category) {
        List<Vragen> connectChoiceQuestions = vragenRepository.find8ConnectChoiceQuestionsByCategory(category);
        List<Vragen> otherQuestions = vragenRepository.find18OtherQuestionsByCategory(category);

        connectChoiceQuestions.addAll(otherQuestions);
        return connectChoiceQuestions;
    }

    public Vragen saveVraag(Vragen vraag) {
        return vragenRepository.save(vraag);
    }

    public void deleteVraag(Long id) {
        vragenRepository.deleteById(id);
    }

    public Vragen getVraagById(Long id) {
        return vragenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vraag niet gevonden met id: " + id));
    }

    public Vragen updateVraag(Vragen updatedVraag) {
        Vragen bestaande = getVraagById(updatedVraag.getId());

        // Update velden
        bestaande.setVraag(updatedVraag.getVraag());
        bestaande.setType(updatedVraag.getType());

        // Verwijder oude antwoorden
        antwoordenRepository.deleteAll(bestaande.getAntwoorden());
        bestaande.getAntwoorden().clear();

        // Voeg nieuwe antwoorden toe (met link naar deze vraag)
        if (updatedVraag.getAntwoorden() != null) {
            for (Antwoorden a : updatedVraag.getAntwoorden()) {
                a.setVragen(bestaande); // belangrijk: bidirectionele link
                bestaande.getAntwoorden().add(a);
            }
        }

        return vragenRepository.save(bestaande);
    }

    public int addVragenFromCsv(MultipartFile file) {
        Logger log = LoggerFactory.getLogger(UserService.class);
        int count = 0;
        int lineNumber = 0;

        log.info("Start verwerken CSV: filename={}, size={} bytes, contentType={}",
                file.getOriginalFilename(), file.getSize(), file.getContentType());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    log.debug("Lege lijn {} - skipped", lineNumber);
                    continue;
                }

                if (firstLine) {
                    log.info("Header lijn {}: {}", lineNumber, line);
                    firstLine = false;
                    continue;
                }

                log.debug("Verwerken lijn {}: {}", lineNumber, line);

                String[] parts = line.split(";");
                if (parts.length < 4) {
                    log.warn("Te weinig kolommen in lijn {} ({} parts) - skipped", lineNumber, parts.length);
                    continue;
                }
                String type = parts[0].trim();
                String vraag = parts[1].trim();
                String category = parts[2].trim();
                String a1 = parts[3].trim();
                String a2 = parts[4].trim();
                String a3 = parts[5].trim();
                String a4 = parts[6].trim();
                int tijd = Integer.parseInt(parts[7].trim());

                Antwoorden antw1 = new Antwoorden(true, a1);
                Antwoorden antw2 = new Antwoorden(false, a2);
                Antwoorden antw3 = new Antwoorden(false, a3);
                Antwoorden antw4 = new Antwoorden(false, a4);

                Vragen newVraag = new Vragen(type, vraag, category, List.of(antw1, antw2, antw3, antw4), null, tijd);

                vragenRepository.save(newVraag);
                count++;

            }

            log.info("CSV verwerking klaar: {} lijnen verwerkt, {} vragen aangemaakt", lineNumber, count);

        } catch (Exception e) {
            log.error("Fout bij inlezen CSV: {}", e.getMessage(), e);
            throw new RuntimeException("Fout bij inlezen CSV: " + e.getMessage());
        }

        return count;
    }

}
