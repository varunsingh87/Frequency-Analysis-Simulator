package frequencyanalysissimulator.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LetterArithmeticTest {
    @Test
    void testToNumberAndToLetterAreInverses() {
        Assertions.assertEquals('A', LetterArithmetic.letterOperator.toLetter(LetterArithmetic.letterOperator.toNumber('A')));
        Assertions.assertEquals('a', LetterArithmetic.letterOperator.toLetter(LetterArithmetic.letterOperator.toNumber('a')));
        Assertions.assertEquals(0, LetterArithmetic.letterOperator.toNumber(LetterArithmetic.letterOperator.toLetter(0)));
    }
}