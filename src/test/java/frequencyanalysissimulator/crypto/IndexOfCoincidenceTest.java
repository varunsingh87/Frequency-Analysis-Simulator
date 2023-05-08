package frequencyanalysissimulator.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IndexOfCoincidenceTest {
    @Test
    public void testSingleRepeatingCharacter() {
        assertEquals(1.0, FrequencyAnalysis.calculateIndexOfCoincidence("DDDDDDD"));
    }

    @Test
    public void testTwoRandomCharacters() {
        assertEquals(0.0, FrequencyAnalysis.calculateIndexOfCoincidence("KD"));
    }

    @Test
    public void testEnglishWord() {
        assertEquals(0.045, FrequencyAnalysis.calculateIndexOfCoincidence("CRYPTOGRAPHY"), 0.001);
    }

    @Test
    public void testShortPhrase() {
        assertEquals(0.14103, FrequencyAnalysis.calculateIndexOfCoincidence("TO BE OR NOT TO BE"), 0.00001);
    }

    @Test
    public void testParagraph() {
        assertEquals(.07222, FrequencyAnalysis.calculateIndexOfCoincidence(
                """
                        That makes calamity of so long life. Than fly to others that we know not of? And lose the name of action."""),
                0.00001);
    }
}
