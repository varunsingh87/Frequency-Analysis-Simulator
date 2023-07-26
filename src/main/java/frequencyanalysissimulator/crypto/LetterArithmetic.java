package frequencyanalysissimulator.crypto;

import java.util.HashMap;

/**
 * Helper class for performing arithmetic on letters using assigned numbers for each letter
 * @implNote supported for 26 letters of English/Arabic alphabet
 */
public final class LetterArithmetic {
    private static final char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private final HashMap<Character, Integer> letters = new HashMap<>(52);
    // TODO: Store a flag in the instance that checks if it's lower case so that the next time toLetter is used you don't need to pass in whether it's lower-case

    static final LetterArithmetic letterOperator = new LetterArithmetic();

    private LetterArithmetic() {
        for (int i = 0; i < 26; i++) {
            letters.put(ALPHABET[i], i + 1);
            letters.put(Character.toLowerCase(ALPHABET[i]), i + 1);
        }
    }
    
    int toNumber(char letter) {
        return letters.containsKey(letter) ? letters.get(letter) : (int) letter;
    }
    
    char toLetter(int index, boolean lowerCase) {
        char letter = ALPHABET[Math.floorMod(index, 26)];
        return lowerCase ? Character.toLowerCase(letter) : letter;
    }
}