package frequencyanalysissimulator.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TrithemiusTest {
    @ParameterizedTest
    @CsvSource({
        "kiqrv exbwp, 3", "mkstx gzdyr, 5", // Natural shifts 0 < n < 26
        "hfnos buytm, 0", "hfnos buytm, 26", // No shift
        "ljrsw fycxq, 30", // Above alphabet n > 26
        "gemnr atxsl, -1", "vtbcg pimha, -12", "omuvz ibfat, -19" // Negative shifts n < 0
    })
    void testEncryptionAscending(String expected, int shift) {
        Assertions.assertEquals(expected, Vigenere.encryptTrithemius("hello world", true, shift));
    }

    @ParameterizedTest
    @CsvSource({
        "okqpr yprkb, 7", "plrqs zqslc, 8",
        "hdjik rikdu, 0", "hdjik rikdu, 26"
    })
    void testDescending(String expected, int shift) {
        Assertions.assertEquals(expected, Vigenere.encryptTrithemius("hello world", false, shift));
        Assertions.assertEquals("hello world", Vigenere.decryptTrithemius(expected, false, shift));
    }

    @ParameterizedTest
    @CsvSource({
        "kiqrv exbwp, 3", "mkstx gzdyr, 5", // Natural shifts 0 < n < 26
        "hfnos buytm, 0", "hfnos buytm, 26",
        "ljrsw fycxq, 30", // Above alphabet n > 26
        "gemnr atxsl, -1", "vtbcg pimha, -12", "omuvz ibfat, -19" // Negative shifts n < 0
    })
    void testDecryptionAscending(String ciphertext, int shift) {
        Assertions.assertEquals("hello world", Vigenere.decryptTrithemius(ciphertext, true, shift));
    }
}