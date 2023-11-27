package frequencyanalysissimulator.crypto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the German "variant Beaufort" cipher
 */
public class VariantBeaufortTest {
    @Test
    void testConversionLower() {
        String originalMessage = "how much wood can a woodchuck chuck";
        String encryptedMessage = "xky cqex sqez eqj c mkqtyjkym sdwsg";

        assertEquals(encryptedMessage, Vigenere.encryptBeaufortVariant("key", originalMessage));
        assertEquals(originalMessage, Vigenere.decryptBeaufortVariant("key", encryptedMessage));
    }

    @Test
    void testConversionUpper() {
        String originalMessage = "HOW MUCH WOOD CAN A WOODCHUCK CHUCK";
        String encryptedMessage = "XKY CQEX SQEZ EQJ C MKQTYJKYM SDWSG";

        assertEquals(encryptedMessage, Vigenere.encryptBeaufortVariant("key", originalMessage));
        assertEquals(originalMessage, Vigenere.decryptBeaufortVariant("key", encryptedMessage));
    }

    @Test
    void testConversion_MixedCase_Multiline() {
        String originalMessage = """
        Peter Piper picked a peck of pickled peppers
        A peck of pickled peppers Peter Piper picked
        """;
        String encryptedMessage = """
        Favun Rylgh lksggt w ruym eb ryymbaf farfati
        W ruym eb ryymbaf farfati Lgjat Ferun ryymuz
        """;

        assertEquals(encryptedMessage, Vigenere.encryptBeaufortVariant("key", originalMessage));
        assertEquals(originalMessage, Vigenere.decryptBeaufortVariant("key", encryptedMessage));
    }

    /**
     * Tests that the casing of the key does not affect the encryption
     */
    @Test
    @DisplayName("Key's casing independent of encryption")
    void testKeyEncryption_AnyCase() {
        String message = "A simple variant is to encrypt.";
        final String ALL_CAPS_KEY = "CRYPTO";
        final String LOWER_CASE_KEY = "crypto";
        final String MIXED_CASE_KEY = "Crypto";

        assertEquals(Vigenere.encryptBeaufortVariant(ALL_CAPS_KEY, message), Vigenere.encryptBeaufortVariant(LOWER_CASE_KEY, message));
        assertEquals(Vigenere.encryptBeaufortVariant(LOWER_CASE_KEY, message), Vigenere.encryptBeaufortVariant(MIXED_CASE_KEY, message));
    }

    @Test
    @DisplayName("Key's casing independent of decryption")
    void testKeyDecryption_AnyCase() {
        String message = "A simple variant is to encrypt";
        final String ALL_CAPS_KEY = "CRYPTO";
        final String LOWER_CASE_KEY = "crypto";
        final String MIXED_CASE_KEY = "Crypto";

        assertEquals(Vigenere.decryptBeaufortVariant(ALL_CAPS_KEY, message), Vigenere.decryptBeaufortVariant(LOWER_CASE_KEY, message));
        assertEquals(Vigenere.decryptBeaufortVariant(LOWER_CASE_KEY, message), Vigenere.decryptBeaufortVariant(MIXED_CASE_KEY, message));
    }
}