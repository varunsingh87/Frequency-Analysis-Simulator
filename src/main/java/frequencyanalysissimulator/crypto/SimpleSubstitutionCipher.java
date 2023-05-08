package frequencyanalysissimulator.crypto;

/**
 * Monoalphabetic substitution cipher
 * 
 * @author Varun Singh on 2/20/2023
 */
public class SimpleSubstitutionCipher {
    private String ciphertext;
    private int[] counts;

    public SimpleSubstitutionCipher() {
        counts = FrequencyAnalysis.calculateAbsoluteLetterFrequencies(ciphertext);
    }

    public String decrypt() {
        return null;
    }

    /**
     * Decrypts an individual Caesar cipher using Chi Square analysis
     * to find which number of shifts matches the standard English frequency
     * distribution the most
     * 
     * @time O(n) - Increases linearly with the length of the ciphertext
     * 
     * @param ciphertext
     *            The cipher text to get the letter rotation of
     * @return The letter that represents the number of rotations for the Caesar
     *         cipher and part of the key for the Vigenere cipher
     */
    private double calculateChiSquare(String keyword) {
        char[] key = new char[26];
        if (keyword.length() >= 26) {
            key = keyword.substring(0, 26).toCharArray();
        } else {

        }

        return 0.0;
    }

}
