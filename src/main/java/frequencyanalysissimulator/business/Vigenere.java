package frequencyanalysissimulator.business;

public class Vigenere {
    private String ciphertext;
    private int keylength;

    public Vigenere(int len, String cipher) {
        keylength = len;
        ciphertext = cipher;
    }

    /**
     * Decrypts an individual Caesar cipher using Chi Square analysis
     * to find which number of shifts matches the standard English frequency
     * distribution the most
     * 
     * @time O(n)
     * 
     * @param coset The nth coset in the Vigenere cipher
     * @return The letter that represents the number of rotations for the Caesar
     *         cipher and part of the key for the Vigenere cipher
     */
    public char decryptCosetByChiSquare(byte coset) {
        double[] ciphertextLetterFrequencies = new double[26];
        // Populate ciphertext letter frequencies by adding one for every occurrence of
        // letter O(n)
        for (char letter : ciphertext.toCharArray()) {

            int asNum = letter - 65;
            // Ignore anything other than letters (such as spaces)
            if (asNum > 0 && asNum <= 25) {
                ciphertextLetterFrequencies[asNum] += 1.0 / ciphertext.length();
            }
        }

        // O(26 * 26) = O(c)
        double[] chiSquareValues = new double[26];
        for (int shift = 0; shift < 26; shift++) {
            double chiSquareValue = 0;
            for (int i = 0; i < 26; i++) {
                double standardFrequency = FrequencyAnalysis.standardEnglishFrequencies[i];
                double shiftedCiphertextFrequency = ciphertextLetterFrequencies[(i + shift) % 26];
                chiSquareValue += Math.pow(shiftedCiphertextFrequency - standardFrequency, 2)
                        / standardFrequency;
            }
            chiSquareValues[shift] = chiSquareValue;
            System.out.println(
                    String.format("Chi-Square Value for %s is %f", (char) (shift + (int) 'A'), chiSquareValue));
        }

        // O(26) = O(c)
        double smallestChiSquareValue = Double.MAX_VALUE;
        char letter = 'A';
        for (int i = 0; i < chiSquareValues.length; i++) {
            if (chiSquareValues[i] < smallestChiSquareValue) {
                smallestChiSquareValue = chiSquareValues[i];
                letter = (char) (i + (int) 'A'); // Convert number to letter
            }
        }

        return letter;
    }
}
