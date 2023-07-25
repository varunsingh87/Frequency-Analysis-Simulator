package frequencyanalysissimulator.crypto;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Beaufort {
    private static String convert(String key, String text) {
        final int keySize = key.length();
        LetterArithmetic l = new LetterArithmetic();
        StringBuilder s = new StringBuilder();
        int nonLetterCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                int keyOffset = l.toNumber(key.charAt((i - nonLetterCount) % keySize));
                int textMapping = l.toNumber(text.charAt(i));
                s.append(l.toLetter(keyOffset - textMapping, Character.isLowerCase(text.charAt(i))));
            } else {
                s.append(text.charAt(i));
                nonLetterCount++;
            }
        }
                
        return s.toString();
    }
    
    public static String encrypt(String key, String text) {
        return convert(key, text);
    }
    
    public static String decrypt(String key, String text) {
        return convert(key, text);
    }
}