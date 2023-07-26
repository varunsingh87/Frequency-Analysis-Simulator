package frequencyanalysissimulator.crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the German "variant Beaufort" cipher
 */
public class VariantBeaufortTest {
    @Test
    void testEncryptLower() {
        String expected = "xky cqex sqez eqj c mkqtyjkym sdwsg wdpkb pju sqezexqea dci yjkymuz gdkwwd yekf";
        String actual = Beaufort.encryptVariant("key", "how much wood can a woodchuck chuck until the woodchuck has chucked enough wood");
        assertEquals(expected, actual);
    }
    
    @Test
    void testEncrypt_MixedCase_Multiline() {
        String originalMessage = """
        Peter Piper picked a peck of pickled peppers
        A peck of pickled peppers Peter Piper picked
        If Peter Piper picked a peck of pickled peppers
        Where's the peck of pickled peppers Peter Piper picked?
        """;
        String expected = """
        Favun Rylgh lksggt w ruym eb ryymbaf farfati
        W ruym eb ryymbaf farfati Lgjat Ferun ryymuz
        Kv Lgjat Ferun ryymuz c faea kh feeahgt lgflgho
        Yxatu'o vxa ruym eb ryymbaf farfati Lgjat Ferun ryymuz?
        """;
        
        assertEquals(expected, Beaufort.encryptVariant("key", originalMessage));
    }
}