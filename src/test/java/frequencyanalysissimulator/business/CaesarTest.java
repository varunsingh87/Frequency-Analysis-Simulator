package frequencyanalysissimulator.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CaesarTest {
    @Test
    public void testEncrypt() {
        Caesar plainToCipher = new Caesar("ATTACKATDAWN");
        assertEquals("FYYFHPFYIFBS", plainToCipher.encrypt("F"));
    }
}
