package frequencyanalysissimulator.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CaesarTest {
    @Test
    public void testEncryptWithNumber() {
        Caesar plainToCipher = new Caesar("ATTACKATDAWN");
        assertEquals("GZZGIQGZJGCT", plainToCipher.encrypt(6));
    }

    @Test
    public void testEncryptWithLetter() {
        Caesar plainToCipher = new Caesar("ATTACKATDAWN");
        assertEquals("FYYFHPFYIFBS", plainToCipher.encrypt("F"));
    }
}
