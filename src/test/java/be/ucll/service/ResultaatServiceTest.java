package be.ucll.service;

import be.ucll.model.Resultaat;
import be.ucll.model.User;
import be.ucll.model.Vragen;
import be.ucll.repository.ResultaatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ResultaatServiceTest {

    @Mock
    private ResultaatRepository resultaatRepository;

    @InjectMocks
    private ResultaatService resultaatService;

    private Resultaat resultaat;
    private User user;
    private Vragen vraag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setName("Henk");
        user.setEmail("henk@hotmail.com");
        user.setUsername("henkie");

        vraag = new Vragen();
        vraag.setVraag("Welk type diabetes komt wereldwijd het meest voor?");

        resultaat = new Resultaat("75%");
        resultaat.setUser(user);
        resultaat.setVraag(vraag);
    }

    @Test
    void testVoegResultaatToe_Success() {
        when(resultaatRepository.save(any(Resultaat.class))).thenReturn(resultaat);

        Resultaat opgeslagen = resultaatService.voegResultaatToe(resultaat);

        assertNotNull(opgeslagen);
        assertEquals("75%", opgeslagen.getResultaat());
        assertEquals("Henk", opgeslagen.getUser().getName());
        assertEquals("Welk type diabetes komt wereldwijd het meest voor?", opgeslagen.getVraag().getVraag());
    }

    @Test
    void testVoegResultaatToe_NullResultaat() {
        assertThrows(IllegalArgumentException.class, () -> {
            resultaatService.voegResultaatToe(null);
        });
    }

    @Test
    void testGetResultaatById_Success() {
        Long id = 1L;
        when(resultaatRepository.findById(id)).thenReturn(Optional.of(resultaat));

        Resultaat gevonden = resultaatService.getResultaatById(id);

        assertNotNull(gevonden);
        assertEquals("75%", gevonden.getResultaat());
        assertEquals("Henk", gevonden.getUser().getName());
        assertEquals("Welk type diabetes komt wereldwijd het meest voor?", gevonden.getVraag().getVraag());
    }

    @Test
    void testGetResultaatById_NietGevonden() {
        Long id = 999L;
        when(resultaatRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            resultaatService.getResultaatById(id);
        });
    }

    @Test
    void testGetResultaatById_NullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            resultaatService.getResultaatById(null);
        });
    }

    @Test
    void testGetAll_ReturnsList() {
        Resultaat resultaat2 = new Resultaat("90%");
        User user2 = new User();
        user2.setName("Piet");
        resultaat2.setUser(user2);
        Vragen vraag2 = new Vragen();
        vraag2.setVraag("Wat is insuline?");
        resultaat2.setVraag(vraag2);

        List<Resultaat> resultaten = Arrays.asList(resultaat, resultaat2);
        when(resultaatRepository.findAll()).thenReturn(resultaten);

        List<Resultaat> gevonden = resultaatService.getAll();

        assertEquals(2, gevonden.size());
        assertEquals("75%", gevonden.get(0).getResultaat());
        assertEquals("90%", gevonden.get(1).getResultaat());
        assertEquals("Henk", gevonden.get(0).getUser().getName());
        assertEquals("Piet", gevonden.get(1).getUser().getName());
    }

    @Test
    void testGetAll_EmptyList() {
        when(resultaatRepository.findAll()).thenReturn(Arrays.asList());

        List<Resultaat> gevonden = resultaatService.getAll();

        assertNotNull(gevonden);
        assertTrue(gevonden.isEmpty());
    }

    @Test
    void testResultaatConstructorAndGetter() {
        Resultaat resultaat = new Resultaat("85%");
        assertEquals("85%", resultaat.getResultaat());
    }

    @Test
    void testResultaatSetterValid() {
        Resultaat resultaat = new Resultaat();
        resultaat.setResultaat("60%");
        assertEquals("60%", resultaat.getResultaat());
    }

    @Test
    void testResultaatSetterNull() {
        Resultaat resultaat = new Resultaat();
        resultaat.setResultaat(null);
        assertEquals("", resultaat.getResultaat());
    }

    @Test
    void testResultaatSetterNoPercentageSign() {
        Resultaat resultaat = new Resultaat();
        assertThrows(IllegalArgumentException.class, () -> resultaat.setResultaat("60"));
    }

    @Test
    void testResultaatSetterAbove100() {
        Resultaat resultaat = new Resultaat();
        assertThrows(IllegalArgumentException.class, () -> resultaat.setResultaat("120%"));
    }

    @Test
    void testResultaatSetterNotANumber() {
        Resultaat resultaat = new Resultaat();
        assertThrows(IllegalArgumentException.class, () -> resultaat.setResultaat("abc%"));
    }

    @Test
    void testToStringContainsResultaat() {
        Resultaat resultaat = new Resultaat("77%");
        String s = resultaat.toString();
        assertTrue(s.contains("resultaaten"));
        assertTrue(s.contains("resultaat=77%"));
    }
}
