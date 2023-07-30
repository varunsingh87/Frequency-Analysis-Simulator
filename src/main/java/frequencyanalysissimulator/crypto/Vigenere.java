package frequencyanalysissimulator.crypto;

import java.util.Collections;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

import static frequencyanalysissimulator.crypto.LetterArithmetic.ALPHABET;
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
                s.append(letterOperator.toLetter(shiftOperationResult));
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
        return convert(key, text, Integer::sum);
    }

    // TODO: Vernam cipher
    // TODO: Autokey cipher

    public static String encrypt(String key, String text) {
        return convert(key, text, (k, l) -> l + k);
    }

    public static String decrypt(String key, String text) {
        return convert(key, text, (k, l) -> l - k);
    }

    public static String encryptGronsfeld(String key, String text) {
        if (!Pattern.matches("[0-9]+", key))
            throw new IllegalArgumentException("Key must be all digits, 0-9");

        StringBuilder mappedKey = new StringBuilder(key.length());
        for (char number : key.toCharArray()) {
            mappedKey.append(letterOperator.toLetter(Integer.parseInt(String.valueOf(number))));
        }

        return encrypt(mappedKey.toString(), text);
    }

    public static String decryptGronsfeld(String key, String text) {
        if (!Pattern.matches("[0-9]+", key))
            throw new IllegalArgumentException("Key must be all digits, 0-9");

        StringBuilder mappedKey = new StringBuilder(key.length());
        for (char number : key.toCharArray()) {
            mappedKey.append(letterOperator.toLetter(Integer.parseInt(String.valueOf(number))));
        }

        return decrypt(mappedKey.toString(), text);
    }

    /**
     * @param plaintext    Message to encipher
     * @param ascending    Evolution of shift: true is ascending, false is descending, both starting at initial shift
     * @param initialShift Initial shift before it evolves
     * @return Trithemius-enciphered ciphertext
     * @implNote Negative initial shifts are supported, by becoming positive shifts that many counts from the end of the alphabet
     */
    public static String encryptTrithemius(String plaintext, boolean ascending, int initialShift) {
        initialShift = Math.floorMod(initialShift, 26);
        String alphabet = String.valueOf(ALPHABET);
        String shiftedAlphabet = alphabet.substring(initialShift) + alphabet.substring(0, initialShift);
        if (ascending) {
            System.out.printf("Shifted Alphabet: %s", shiftedAlphabet);
            return encrypt(shiftedAlphabet, plaintext);
        } else {
            shiftedAlphabet = shiftedAlphabet.charAt(0) + new StringBuilder(shiftedAlphabet.substring(1)).reverse().toString();
            return encrypt(shiftedAlphabet, plaintext);
        }
    }
}