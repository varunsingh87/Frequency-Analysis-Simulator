package frequencyanalysissimulator.crypto;

import java.util.function.BiFunction;

import static frequencyanalysissimulator.crypto.LetterArithmetic.letterOperator;

public class Vigenere {
    private static String convert(String key, String text, BiFunction<Integer, Integer, Integer> shiftOperation) {
        final int keySize = key.length();
        StringBuilder s = new StringBuilder();
        int nonLetterCount = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                int keyOffset = letterOperator.toNumber(key.charAt((i - nonLetterCount) % keySize));
                int textMapping = letterOperator.toNumber(text.charAt(i));
                int shiftOperationResult = shiftOperation.apply(keyOffset, textMapping);
                s.append(letterOperator.toLetter(shiftOperationResult, Character.isLowerCase(text.charAt(i))));
            } else {
                s.append(text.charAt(i));
                nonLetterCount++;
            }
        }

        return s.toString();
    }

    public static String encryptClassic(String key, String text) {
        return convert(key, text, (k, l) -> k - l);
    }

    public static String decryptClassic(String key, String text) {
        return convert(key, text, (k, l) -> k - l);
    }

    public static String encryptVariant(String key, String text) {
        return convert(key, text, (k, l) -> l - k);
    }

    public static String decryptVariant(String key, String text) {
        return convert(key, text, (k, l) -> l - 2 + k);
    }

    public static String encrypt(String key, String text) {
        return convert(key, text, (k, l) -> l - 2 + k);
    }

    public static String decrypt(String key, String text) {
        return convert(key, text, (k, l) -> l - k);
    }
}