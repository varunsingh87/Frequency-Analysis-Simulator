package frequencyanalysissimulator.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GronsfeldTest {
    @Test
    @DisplayName("Throws when key isn't all digits")
    void testInvalidKeyThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Vigenere.encryptGronsfeld("rghfa1", "Hello world!");
            Vigenere.decryptGronsfeld("rghfa1", "Hello world!");
        });
    }
    
    @Test
    void testValidKeyEncryptDecryptIsInverse() {
        Assertions.assertEquals("Hello world!", Vigenere.decryptGronsfeld("1234", Vigenere.encryptGronsfeld("1234", "Hello world!")));
    }
    
    @Test
    void testEncryptsCorrectly() {
        Assertions.assertEquals("Igopt mccc bptoh", Vigenere.encryptGronsfeld("12345", "Hello lazy world"));
        Assertions.assertEquals("Igopt, mccc bptoh!", Vigenere.encryptGronsfeld("12345", "Hello, lazy world!"));
    }
    
    @Test
    void testDecryptsCorrectly() {
        Assertions.assertEquals("Sixty zippers were quickly picked from the woven jute bag", Vigenere.decryptGronsfeld("6453", "Ymcwe dnsviwv ciwh wynfqpd sogphj jwrs xmh csaht nzwk ffj"));
    }
}