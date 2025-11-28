package be.ucll.model;

import org.junit.jupiter.api.Test;

public class VraagTest {
    @Test
    public void testVraagCreation() {
        Vragen vraag = new Vragen("meerkeuze","Wat is de hoofdstad van Frankrijk?", null, null);
        assert(vraag.getType().equals("meerkeuze"));
        assert(vraag.getVraag().equals("Wat is de hoofdstad van Frankrijk?"));
        assert(vraag.getAntwoorden() == null);
        assert(vraag.getId() == null);
    }

    @Test
    public void testVraagCreationNoType() {
        Vragen vraag = new Vragen(null,"Wat is de hoofdstad van Frankrijk?", null, null);
        assert(vraag.getType() == null);
        assert(vraag.getVraag().equals("Wat is de hoofdstad van Frankrijk?"));
    }

    @Test
    public void testVraagCreationNoVraag() {
        Vragen vraag = new Vragen("meerkeuze",null, null, null);
        assert(vraag.getType().equals("meerkeuze"));
        assert(vraag.getVraag() == null);
    }
}
