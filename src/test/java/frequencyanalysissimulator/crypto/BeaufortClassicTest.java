package frequencyanalysissimulator.crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeaufortClassicTest {
    @Test
    void testMulticaseEncryption() {
        String actualCiphertext = Vigenere.encryptBeaufortClassic("KEY", "dCode Beaufort");
        String expected = "hCkha Xgeefqhr";
        
        assertEquals(expected, actualCiphertext);
    }
    
    @Test
    void testMulticaseDecryption() {
        String actualPlaintext = Vigenere.decryptBeaufortClassic("KEY", "hCkha Xgeefqhr");
        String expected = "dCode Beaufort";
        
        assertEquals(expected, actualPlaintext);
    }
    
    @Test
    void testPunctuatedEncryptionAndDecryption() {
        String originalMessage = "The five boxing wizards jump quickly.";
        
        String actualCiphertext = Vigenere.encryptBeaufortClassic("KEY", originalMessage);
        String expectedCiphertext = "Rxu fwdg dknwle iqlehhm pqsj ukqiunm.";
        assertEquals(expectedCiphertext, actualCiphertext);
        
        String decryptedText = Vigenere.decryptBeaufortClassic("KEY", actualCiphertext);
        assertEquals(originalMessage, decryptedText);
    }
}