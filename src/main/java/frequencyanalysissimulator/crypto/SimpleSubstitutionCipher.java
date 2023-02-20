package frequencyanalysissimulator.crypto;

/**
 * Monoalphabetic substitution cipher
 * 
 * @author Varun Singh on 2/20/2023
 */
public class SimpleSubstitutionCipher implements Cipher {
    private String ciphertext;
    private double[] frequencies;

    @Override
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
    private double calculateChiSquare() {
        // Populate ciphertext letter frequencies by adding one for every occurrence of
        // letter O(n)
        for (int i = 0; i < ciphertext.length(); i++) {
            char letter = ciphertext.charAt(i);
            int asNum = letter - 65;
            // Ignore anything other than letters (such as spaces)
            if (Character.isAlphabetic((int) letter)) {
                frequencies[asNum] += 1.0 / ciphertext.length();
            }
        }

        double chiSquareValue = 0;
        for (int i = 0; i < 26; i++) {
            double standardFrequency = FrequencyAnalysis.standardEnglishFrequencies[i];
            double shiftedCiphertextFrequency = frequencies[i % 26];
            chiSquareValue += Math.pow(shiftedCiphertextFrequency - standardFrequency, 2) / standardFrequency;
        }

        return chiSquareValue;
    }

}
