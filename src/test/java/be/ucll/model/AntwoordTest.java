package be.ucll.model;

import org.junit.jupiter.api.Test;

public class AntwoordTest {
    @Test
    public void testAntwoordCreation() {
        Antwoorden antwoord = new Antwoorden(true, "N³");
        assert(antwoord.getAntwoord().equals("N³"));
        assert(antwoord.getCorrect().equals(true));
    }
}