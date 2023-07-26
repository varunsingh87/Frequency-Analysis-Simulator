package frequencyanalysissimulator.crypto;

import static frequencyanalysissimulator.crypto.LetterArithmetic.letterOperator;

public class Beaufort {
    private static String convert(String key, String text) {
        final int keySize = key.length();
        StringBuilder s = new StringBuilder();
        int nonLetterCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                int keyOffset = letterOperator.toNumber(key.charAt((i - nonLetterCount) % keySize));
                int textMapping = letterOperator.toNumber(text.charAt(i));
                s.append(letterOperator.toLetter(keyOffset - textMapping, Character.isLowerCase(text.charAt(i))));
            } else {
                s.append(text.charAt(i));
                nonLetterCount++;
            }
        }
                
        return s.toString();
    }
    
    public static String encryptClassic(String key, String text) {
        return convert(key, text);
    }
    
    public static String decryptClassic(String key, String text) {
        return convert(key, text);
    }

    public static String encryptVariant(String key, String text) {
        String classicEncrypted = convert(key, text);
        StringBuilder s = new StringBuilder();

        for (char letter : classicEncrypted.toCharArray()) {
            if (Character.isLetter(letter)) {
                int inverted = 27 - letterOperator.toNumber(letter);
                s.append(letterOperator.toLetter(inverted, Character.isLowerCase(letter)));
            } else {
                s.append(letter);
            }
        }

        return s.toString();
    }

    public static String decryptVariant(String key, String text) {
        return convert(text, key);
    }
}