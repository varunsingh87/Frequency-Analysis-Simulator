package frequencyanalysissimulator.crypto;

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

    @Test
    public void testDecryptWithNumber() {
        int key = 7;
        Caesar c = new Caesar(Caesar.encrypt("ATTACKATDAWN", key));
        assertEquals("ATTACKATDAWN", c.decrypt(key));
    }

    @Test
    public void testKasiski() {
        int key = 7;
        Caesar c = new Caesar(Caesar.encrypt("ETTECKETDEWN", key));
        assertEquals("ETTECKETDEWN", c.decrypt(key));
    }
}
