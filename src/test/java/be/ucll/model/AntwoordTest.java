package be.ucll.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AntwoordTest {
    @Test
    public void testAntwoordCreation() {
        Antwoorden antwoord = new Antwoorden(true, "N³");
        assert(antwoord.getAntwoord().equals("N³"));
        assert(antwoord.getCorrect().equals(true));
    }

    @Test
    public void testAntwoordModification() {
        Antwoorden antwoord = new Antwoorden(true, "N³");
        antwoord.setAntwoord("N²");
        antwoord.setCorrect(false);
        assert(antwoord.getAntwoord().equals("N²"));
        assert(antwoord.getCorrect().equals(false));
    }

    @Test
    public void testAntwoordMetSpecialeKarakters() {
        Antwoorden antwoord = new Antwoorden(true, "2²+π=7.14");
        assertEquals("2²+π=7.14", antwoord.getAntwoord(), 
            "Antwoord moet speciale karakters kunnen bevatten");
    }

    @Test
    public void testLeegAntwoord() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Antwoorden(true, "");
        }, "Leeg antwoord moet niet toegestaan zijn");
    }
}