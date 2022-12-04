package frequencyanalysissimulator.business;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class Vigenere implements Cipher {
    private String cipherText;
    private char[] cipherChars;

    private int keylength;

    public Vigenere(int len, String cipher) {
        // Remove anything that is not a letter
        cipherText = cipher.replaceAll("[^A-Za-z]", "").toUpperCase();
        cipherChars = cipherText.toCharArray();
        keylength = len;
    }

    public Vigenere(String cipher) {
        cipherText = cipher.replaceAll("[^A-Za-z]", "").toUpperCase();
        cipherChars = cipherText.toCharArray();
        keylength = this.calculateKeyLengthByFriedmanTest();
    }

    /**
     * @return the inputChars
     */
    public char[] getInputChars() {
        return cipherChars;
    }

    /**
     * @param inputChars the inputChars to set
     */
    public void setInputChars(char[] inputChars) {
        this.cipherChars = inputChars;
    }

    private String[] distributeCiphertextIntoCosets() {
        String[] cosets = new String[keylength];
        Arrays.fill(cosets, "");
        for (int i = 0; i < cipherText.length(); i++) {
            cosets[i % keylength] += cipherText.charAt(i);
        }
        return cosets;
    }

    /**
     * Use coincidence counting to deduce the key length
     * 
     * @return
     */
    public int calculateKeyLength() {
        HashMap<String, Integer> trigrams = new HashMap<String, Integer>();
        TreeSet<Integer> possibleKeyLengths = new TreeSet<>();

        // O(n - 2) = O(n)
        for (int i = 0; i < cipherText.length() - 2; i++) {
            String trigram = cipherText.substring(i, i + 2);

            if (trigrams.containsKey(trigram)) {
                int prevBigramDistance = trigrams.get(trigram);
                int distance = i - prevBigramDistance;

                trigrams.replace(trigram, distance);
            } else {
                trigrams.put(trigram, i);
            }
        }

        return 3;
    }

    /**
     * Friedman's Test on a possible key length
     * 
     * @param length
     * @return
     */
    float calculateIndexOfCoincidence() {
        float indexOfCoincidence = 0;

        int[] ciphertextLetterCounts = FrequencyAnalysis.calculateAbsoluteLetterFrequencies(cipherText);

        for (int i = 0; i < 26; i++) {
            double countTimesCountMinusOne = ciphertextLetterCounts[i] * (ciphertextLetterCounts[i] - 1);
            System.out.printf("[Vigenere#calculateIndexOfCoincidence] n[%d](n[%d]-1): %f\n", i, i,
                    countTimesCountMinusOne);
            indexOfCoincidence += countTimesCountMinusOne;
        }

        indexOfCoincidence /= cipherText.length() * (cipherText.length() - 1);

        return indexOfCoincidence;
    }

    int calculateKeyLengthByFriedmanTest() {
        float ioc = this.calculateIndexOfCoincidence();

        return Math.round((0.067f - 0.0385f) / (ioc - 0.0385f));
    }

    boolean isPolyalphabetic() {
        double ioc = this.calculateIndexOfCoincidence();
        return ioc >= 0.0661 && ioc <= 0.065;
    }

    public String getKey() {
        String key = "";
        String[] cosets = this.distributeCiphertextIntoCosets();

        for (int i = 0; i < keylength; i++) {
            Caesar coset = new Caesar(cosets[i]);
            key += coset.getKeyByChiSquare();
        }

        return key;
    }

    @Override
    public String decrypt() {
        String plaintext = "";
        String[] cosets = this.distributeCiphertextIntoCosets();

        for (int i = 0; i < keylength; i++) {
            Caesar coset = new Caesar(cosets[i]);
            cosets[i] = coset.decrypt();
        }

        for (int i = 0; i < cipherText.length(); i++) {
            plaintext += cosets[i % keylength].charAt((int) Math.ceil(i / keylength));
        }

        return plaintext;
    }

    public static String encrypt(String plaintext, String key) {
        String ciphertext = "";
        plaintext = plaintext.replaceAll("[^A-Za-z]", "").toUpperCase();

        for (int i = 0; i < plaintext.length(); i++) {
            char letter = Character.toUpperCase(plaintext.charAt(i));

            // Subtract 65 from each character to get the nth letter of the alphabet
            // Confine to 0-25 with % 26
            int shifted = (letter - 65 + key.charAt(i % key.length()) - 65) % 26;
            // Add back 65 when turning to a character (does not need -1 because we did not
            // add 1 above)
            ciphertext += (char) (shifted + 'A');

        }

        return ciphertext;
    }
}
