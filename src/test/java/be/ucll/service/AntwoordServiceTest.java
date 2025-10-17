package be.ucll.service;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import be.ucll.model.Antwoorden;
// import be.ucll.repository.AntwoordenRepository;

public class AntwoordServiceTest {
    
    // @Mock
    // private AntwoordenRepository antwoordenRepository;

    // @InjectMocks
    // private AntwoordenService antwoordenService;

    // @BeforeEach
    // void setUp() {
    //     MockitoAnnotations.openMocks(this);
    // }

    // @Test
    // void testVoegAntwoordToe_Success() {
    //     Antwoorden antwoord = new Antwoorden(true, "Dit is een correct antwoord");
    //     when(antwoordenRepository.save(any(Antwoorden.class))).thenReturn(antwoord);
        
    //     Antwoorden resultaat = antwoordenService.voegAntwoordToe(antwoord);
        
    //     assertNotNull(resultaat);
    //     assertEquals("Dit is een correct antwoord", resultaat.getAntwoord());
    //     assertTrue(resultaat.getCorrect());
    // }

    // @Test
    // void testVindAntwoordById_Success() {
    //     Long id = 1L;
    //     Antwoorden antwoord = new Antwoorden(true, "Test antwoord");
    //     when(antwoordenRepository.findById(id)).thenReturn(java.util.Optional.of(antwoord));
        
    //     Antwoorden gevondenAntwoord = antwoordenService.getAntwoordById(id);
        
    //     assertNotNull(gevondenAntwoord);
    //     assertEquals("Test antwoord", gevondenAntwoord.getAntwoord());
    // }

    // @Test
    // void testVoegAntwoordToe_MetLeegAntwoord() {
    //     Antwoorden antwoord = new Antwoorden(true, "");
        
    //     assertThrows(IllegalArgumentException.class, () -> {
    //         antwoordenService.voegAntwoordToe(antwoord);
    //     });
    // }

    // @Test
    // void testVindAntwoordById_NietGevonden() {
    //     Long id = 999L;
    //     when(antwoordenRepository.findById(id)).thenReturn(java.util.Optional.empty());
        
    //     assertThrows(RuntimeException.class, () -> {
    //         antwoordenService.getAntwoordById(id);
    //     });
    // }
}
