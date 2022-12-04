package frequencyanalysissimulator.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CaesarTest {
    @Test
    public void testEncryptWithNumber() {
        assertEquals("GZZGIQGZJGCT", Caesar.encrypt("ATTACKATDAWN", 6));
    }

    @Test
    public void testEncryptWithLetter() {
        assertEquals("FYYFHPFYIFBS", Caesar.encrypt("ATTACKATDAWN", "F"));
    }
}
