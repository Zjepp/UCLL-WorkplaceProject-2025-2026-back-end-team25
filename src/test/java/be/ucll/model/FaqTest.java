package be.ucll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FaqTest {
    
    @Test
    public void testFaqCreation() {
        Faq faq = new Faq("Is deze app betalend?", "ja deze app is betalend");
        assertNotNull(faq);
        assertEquals("Is deze app betalend?", faq.getQuestion());
        assertEquals("ja deze app is betalend", faq.getAnswer());
    }

    @Test
    public void testFaqCreationWithEmptyQuestion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Faq("", "Some answer");
        });

        String expectedMessage = "Question cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFaqCreationWithNullQuestion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Faq(null, "Some answer");
        });

        String expectedMessage = "Question cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFaqCreationWithEmptyAnswer() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Faq("Is this a question?", "");
        });

        String expectedMessage = "Answer cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFaqCreationWithNullAnswer() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Faq("Is this a question?", null);
        });

        String expectedMessage = "Answer cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFaqCreationWithWhitespaceQuestion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Faq("   ", "Some answer");
        });

        String expectedMessage = "Question cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFaqCreationWithWhitespaceAnswer() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Faq("Is this a question?", "   ");
        });

        String expectedMessage = "Answer cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFaqSetQuestion() {
        Faq faq = new Faq("Original question", "Original answer");
        faq.setQuestion("Updated question");
        assertEquals("Updated question", faq.getQuestion());
    }

    @Test
    public void testFaqSetAnswer() {
        Faq faq = new Faq("Original question", "Original answer");
        faq.setAnswer("Updated answer");
        assertEquals("Updated answer", faq.getAnswer());
    }

    @Test
    public void testFaqSetEmptyQuestion() {
        Faq faq = new Faq("Original question", "Original answer");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            faq.setQuestion("");
        });

        String expectedMessage = "Question cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFaqSetEmptyAnswer() {
        Faq faq = new Faq("Original question", "Original answer");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            faq.setAnswer("");
        });

        String expectedMessage = "Answer cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFaqDefaultConstructor() {
        Faq faq = new Faq();
        assertNotNull(faq);
    }

    @Test
    public void testFaqIdSetterGetter() {
        Faq faq = new Faq("Question", "Answer");
        faq.setId(1L);
        assertEquals(1L, faq.getId());
    }
}
