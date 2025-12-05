package be.ucll.service;

import be.ucll.model.Faq;
import be.ucll.repository.FaqRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FaqServiceTest {

    @Mock
    private FaqRepository faqRepository;

    @InjectMocks
    private FaqService faqService;

    private Faq faq1;
    private Faq faq2;
    private Faq faq3;

    @BeforeEach
    public void setUp() {
        faq1 = new Faq("Is deze app betalend?", "ja deze app is betalend");
        faq1.setId(1L);

        faq2 = new Faq("Hoe kan ik mijn wachtwoord resetten?", "Klik op \"wachtwoord vergeten\" op de loginpagina");
        faq2.setId(2L);

        faq3 = new Faq("Kan ik de app offline gebruiken?", "Nee, je hebt een internetverbinding nodig om de quiz te spelen");
        faq3.setId(3L);
    }

    @Test
    public void testGetAllFaqs() {
        // Arrange
        List<Faq> expectedFaqs = Arrays.asList(faq1, faq2, faq3);
        when(faqRepository.findAll()).thenReturn(expectedFaqs);

        // Act
        List<Faq> actualFaqs = faqService.getAllFaqs();

        // Assert
        assertNotNull(actualFaqs);
        assertEquals(3, actualFaqs.size());
        assertEquals(expectedFaqs, actualFaqs);
        verify(faqRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllFaqsEmptyList() {
        // Arrange
        when(faqRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Faq> actualFaqs = faqService.getAllFaqs();

        // Assert
        assertNotNull(actualFaqs);
        assertEquals(0, actualFaqs.size());
        verify(faqRepository, times(1)).findAll();
    }

    // @Test
    // public void testAddFaq() {
    //     // Arrange
    //     Faq newFaq = new Faq("Nieuwe vraag?", "Nieuw antwoord");
    //     when(faqRepository.save(newFaq)).thenReturn(newFaq);

    //     // Act
    //     Faq savedFaq = faqService.addFaq(newFaq);

    //     // Assert
    //     assertNotNull(savedFaq);
    //     assertEquals("Nieuwe vraag?", savedFaq.getQuestion());
    //     assertEquals("Nieuw antwoord", savedFaq.getAnswer());
    //     verify(faqRepository, times(1)).save(newFaq);
    // }

    // @Test
    // public void testAddFaqWithId() {
    //     // Arrange
    //     Faq newFaq = new Faq("Test vraag?", "Test antwoord");
    //     newFaq.setId(10L);
    //     when(faqRepository.save(newFaq)).thenReturn(newFaq);

    //     // Act
    //     Faq savedFaq = faqService.addFaq(newFaq);

    //     // Assert
    //     assertNotNull(savedFaq);
    //     assertEquals(10L, savedFaq.getId());
    //     assertEquals("Test vraag?", savedFaq.getQuestion());
    //     assertEquals("Test antwoord", savedFaq.getAnswer());
    //     verify(faqRepository, times(1)).save(newFaq);
    // }

    // @Test
    // public void testAddMultipleFaqs() {
    //     // Arrange
    //     Faq faq4 = new Faq("Vraag 4?", "Antwoord 4");
    //     Faq faq5 = new Faq("Vraag 5?", "Antwoord 5");
        
    //     when(faqRepository.save(faq4)).thenReturn(faq4);
    //     when(faqRepository.save(faq5)).thenReturn(faq5);

    //     // Act
    //     Faq saved4 = faqService.addFaq(faq4);
    //     Faq saved5 = faqService.addFaq(faq5);

    //     // Assert
    //     assertNotNull(saved4);
    //     assertNotNull(saved5);
    //     assertEquals("Vraag 4?", saved4.getQuestion());
    //     assertEquals("Vraag 5?", saved5.getQuestion());
    //     verify(faqRepository, times(2)).save(any(Faq.class));
    // }
}
