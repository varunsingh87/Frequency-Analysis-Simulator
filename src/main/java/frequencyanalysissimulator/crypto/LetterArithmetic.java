package frequencyanalysissimulator.crypto;

import java.util.HashMap;

/**
 * Helper class for performing arithmetic on letters using assigned numbers for each letter
 *
 * @implNote supported for 26 letters of English/Arabic alphabet
 * @apiNote 1-indexed
 */
final class LetterArithmetic {
    private static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final HashMap<Character, Integer> letters = new HashMap<>(52);

    private boolean isLowerCase;

    static final LetterArithmetic letterOperator = new LetterArithmetic();

    private LetterArithmetic() {
        for (int i = 0; i < 26; i++) {
            letters.put(ALPHABET[i], i);
            letters.put(Character.toLowerCase(ALPHABET[i]), i);
        }
    }

    /**
     * Converts letter to number
     *
     * @apiNote Side effect-updates isLowerCase flag for conversion in {@link LetterArithmetic#toLetter(int)}
     */
    int toNumber(char letter) {
        isLowerCase = Character.isLowerCase(letter);
        return letters.containsKey(letter) ? letters.get(letter) : (int) letter;
    }

    char toLetter(int index, boolean lowerCase) {
        char letter = ALPHABET[Math.floorMod(index, 26)];
        return lowerCase ? Character.toLowerCase(letter) : letter;
    }

    char toLetter(int index) {
        char letter = ALPHABET[Math.floorMod(index, 26)];
        return isLowerCase ? Character.toLowerCase(letter) : letter;
    }
}