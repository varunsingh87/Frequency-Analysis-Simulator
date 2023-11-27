package frequencyanalysissimulator.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

public class RunningKeyTest {
    @RepeatedTest(value=4)
    void testRunningKeyIsNotRepeated() {
        String[] encryption = Vigenere.encryptRunningKey("THE QUICK BROWN FOX");
        Assertions.assertFalse(encryption[0].isEmpty());
        Assertions.assertFalse(encryption[0].isBlank());
        Assertions.assertEquals(encryption[1].indexOf(encryption[1].substring(0, 3)), encryption[1].lastIndexOf(encryption[1].substring(0, 3)));
        Assertions.assertEquals(encryption[1].indexOf(encryption[1].substring(3, 7)), encryption[1].lastIndexOf(encryption[1].substring(3, 7)));
    }
    
    @RepeatedTest(value=2)
    void testKeyReturnedIsRight() {
        String[] encryption = Vigenere.encryptRunningKey("THE QUICK BROWN FOX");
        Assertions.assertEquals("THE QUICK BROWN FOX", Vigenere.decrypt(encryption[1], encryption[0]));
    }
}