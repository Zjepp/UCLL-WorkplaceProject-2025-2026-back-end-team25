package be.ucll.service;

import be.ucll.model.Vragen;
import be.ucll.repository.VragenRepository;
import be.ucll.repository.AntwoordenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VraagServiceTest {

    @Mock
    private VragenRepository vraagRepository;

    @Mock
    private AntwoordenRepository antwoordenRepository;

    @InjectMocks
    private VragenService vragenService;

    private Vragen vraag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vraag = new Vragen();
        vraag.setId(1L);
        vraag.setVraag("Wat is Java?");
    }

    @Test
    void testAddVraag_Success() {
        when(vraagRepository.save(any(Vragen.class))).thenReturn(vraag);

        Vragen saved = vragenService.saveVraag(vraag);

        assertNotNull(saved);
        assertEquals("Wat is Java?", saved.getVraag());
    }

    @Test
    void testGetVraagById_Success() {
        when(vraagRepository.findById(1L)).thenReturn(Optional.of(vraag));

        Vragen found = vragenService.getVraagById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
        assertEquals("Wat is Java?", found.getVraag());
    }

    @Test
    void testGetVraagById_NotFound() {
        when(vraagRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> vragenService.getVraagById(2L));
    }

    @Test
    void testGetAllVragen() {
        Vragen vraag2 = new Vragen();
        vraag2.setId(2L);
        vraag2.setVraag("Wat is Python?");

        when(vraagRepository.findAll()).thenReturn(Arrays.asList(vraag, vraag2));

        List<Vragen> vragen = vragenService.getAll();

        assertEquals(2, vragen.size());
        assertTrue(vragen.contains(vraag));
        assertTrue(vragen.contains(vraag2));
    }

    @Test
    void testUpdateVraag_Success() {
        when(vraagRepository.findById(1L)).thenReturn(Optional.of(vraag));
        when(vraagRepository.save(any(Vragen.class))).thenAnswer(i -> i.getArgument(0));

        Vragen updatedVraag = new Vragen();
        updatedVraag.setId(1L);
        updatedVraag.setVraag("Nieuwe vraag");

        Vragen result = vragenService.updateVraag(updatedVraag);

        assertNotNull(result);
        assertEquals("Nieuwe vraag", result.getVraag());
    }

    @Test
    void testUpdateVraag_NotFound() {
        when(vraagRepository.findById(99L)).thenReturn(Optional.empty());

        Vragen updatedVraag = new Vragen();
        updatedVraag.setId(99L);
        updatedVraag.setVraag("Niet bestaand");
        assertThrows(RuntimeException.class, () -> vragenService.updateVraag(updatedVraag));
    }

    @Test
    void testDeleteVraag_Success() {
        when(vraagRepository.findById(1L)).thenReturn(Optional.of(vraag));
        doNothing().when(vraagRepository).deleteById(1L);

        assertDoesNotThrow(() -> vragenService.deleteVraag(1L));
        verify(vraagRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteVraag_NotFound() {
        when(vraagRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> vragenService.deleteVraag(99L));
    }
}
