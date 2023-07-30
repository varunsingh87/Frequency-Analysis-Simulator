package frequencyanalysissimulator.crypto;

import java.util.HashMap;

/**
 * Helper class for performing arithmetic on letters using assigned numbers for each letter
 *
 * @implNote supported for 26 letters of English/Arabic alphabet
 */
public final class LetterArithmetic {
    private static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final HashMap<Character, Integer> letters = new HashMap<>(52);

    private boolean isLowerCase;

    static final LetterArithmetic letterOperator = new LetterArithmetic();

    private LetterArithmetic() {
        for (int i = 0; i < 26; i++) {
            letters.put(ALPHABET[i], i + 1);
            letters.put(Character.toLowerCase(ALPHABET[i]), i + 1);
        }
    }

    /**
     * Converts letter to number
     *
     * @throws IllegalArgumentException if not a letter
     * @apiNote Side effect-updates isLowerCase flag for conversion in {@link LetterArithmetic#toLetter(int)}
     */
    int toNumber(char letter) {
        if (!letters.containsKey(letter))
            throw new IllegalArgumentException("Argument must be a letter in the English alphabet");

        isLowerCase = Character.isLowerCase(letter);
        return letters.get(letter);
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