package be.ucll.model;

import org.junit.jupiter.api.Test;

public class ResultaatTest {
    @Test
    public void testResultaatCreation() {
        Resultaat resultaat = new Resultaat("80%");
        assert(resultaat.getResultaat().equals("80%"));
    }
}
