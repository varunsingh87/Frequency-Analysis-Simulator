package frequencyanalysissimulator.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class TrithemiusTest {
    @ParameterizedTest
    @CsvSource({
    "kiqrv exbwp, 3", "ljrsw fycxq, 4", "mkstx gzdyr, 5", "hfnos buytm, 0", "hfnos buytm, 26", "ljrsw fycxq, 30"})
    void testEncryptionWithShift_Ascending(String expected, int shift) {
        Assertions.assertEquals(expected, Vigenere.encryptTrithemius("hello world", true, shift));
    }
}