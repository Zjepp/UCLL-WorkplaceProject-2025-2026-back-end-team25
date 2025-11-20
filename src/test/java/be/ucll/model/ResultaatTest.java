package be.ucll.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultaatTest {
    @Test
    public void testResultaatAanmaken() {
        Resultaat resultaat = new Resultaat("80%");
        assert(resultaat.getResultaat().equals("80%"));
    }

    @Test
    public void testResultaatWijzigen() {
        Resultaat resultaat = new Resultaat("80%");
        resultaat.setResultaat("90%");
        assert(resultaat.getResultaat().equals("90%"));
    }

    @Test
    public void testOngeldigPercentageWaarde() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Resultaat("101%");
        });
    }

    @Test
    public void testOngeldigResultaatFormaat() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Resultaat("ongeldig");
        });
    }
}
