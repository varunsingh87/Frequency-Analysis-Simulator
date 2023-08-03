package frequencyanalysissimulator.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AutokeyTest {
    @Test
    void testEncryptCaseMatchesPlaintext() {
        Assertions.assertEquals("WMPMMXXAEYHBRYOCA", Vigenere.encryptAutokey("KILT", "MEETATTHEFOUNTAIN"));
        Assertions.assertEquals("wmpmmxxaeyhbryoca", Vigenere.encryptAutokey("KILT", "meetatthefountain"));
        Assertions.assertEquals("WMPMMXXAEYHBRYOCA", Vigenere.encryptAutokey("Kilt", "MEETATTHEFOUNTAIN"));
        Assertions.assertEquals("Wmpmmxxaeyhbryoca", Vigenere.encryptAutokey("kilt", "Meetatthefountain"));
    }
    
    @ParameterizedTest
    @CsvSource({ "ATTACKATDAWN, QUEENLY, QNXEPVYTWTWP", "MEETMEATTHECORNER, KING, WMRZYIEMFLEVHYRGF", "THEQUICKBROWNFOX, FRIDGE, YYMTAMVRFHIEPPPO", "THEBRITISHARECOMINGTHEBRITISHARECOMING, USA, NZEUYMUZAAIJLCFQKBSBUKUYMUZAAIJLCFQKBS" })
    void testDecrypt(String plaintext, String primer, String ciphertext) {
        Assertions.assertEquals(plaintext, Vigenere.decryptAutokey(primer, ciphertext));
    }
}