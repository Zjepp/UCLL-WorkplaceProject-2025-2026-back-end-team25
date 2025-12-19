package be.ucll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ucll.model.Antwoorden;
import be.ucll.model.Faq;
import be.ucll.model.Faq;
import be.ucll.repository.FaqRepository;

@Service
public class FaqService {

    private FaqRepository faqRepository;

    @Autowired
    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public List<Faq> getAllFaqs() {
        return faqRepository.findAll();
    }

    public Faq addFaq(Faq faq) {
        return faqRepository.save(faq);
    }

    public void deleteFaq(Long id) {
        faqRepository.deleteById(id);
    }

    public Faq getFaqById(Long id) {
        return faqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("faq niet gevonden met id: " + id));
    }

    public Faq updateFaq(Faq updatedFaq) {
        Faq bestaande = getFaqById(updatedFaq.getId());

        // Update velden
        bestaande.setQuestion(updatedFaq.getQuestion());
        bestaande.setAnswer(updatedFaq.getAnswer());

        return faqRepository.save(bestaande);
    }

    public Faq saveFaq(Faq faq) {
        faqRepository.save(faq);
        return faq;
    }
}
