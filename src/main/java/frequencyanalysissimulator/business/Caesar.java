package frequencyanalysissimulator.business;

public class Caesar implements Cipher {
    private String inputText;
    private int[] ciphertextAsNumbers;

    public Caesar(String t) {
        inputText = String.join("", t.split("[ \r\t\n]")).toUpperCase();
        ciphertextAsNumbers = new int[t.length()];
    }

    /**
     * Decrypts an individual Caesar cipher using Chi Square analysis
     * to find which number of shifts matches the standard English frequency
     * distribution the most
     * 
     * @time O(n) - Increases linearly with the length of the ciphertext
     * 
     * @param inputText The Caesar cipher text to get the letter rotation of
     * @return The letter that represents the number of rotations for the Caesar
     *         cipher and part of the key for the Vigenere cipher
     */
    public char getKeyByChiSquare() {
        double[] ciphertextLetterFrequencies = new double[26];
        // Populate ciphertext letter frequencies by adding one for every occurrence of
        // letter O(n)
        for (int i = 0; i < inputText.length(); i++) {
            char letter = inputText.charAt(i);
            int asNum = letter - 65;
            ciphertextAsNumbers[i] = asNum;
            // Ignore anything other than letters (such as spaces)
            if (asNum > 0 && asNum <= 25) {
                ciphertextLetterFrequencies[asNum] += 1.0 / inputText.length();
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

    @Override
    public String decrypt() {
        char keyLet = getKeyByChiSquare();
        int keyLetAsNum = 26 - (keyLet - 64);
        String key = String.valueOf((char) (keyLetAsNum + 65));
        return this.encrypt(key);
    }

    @Override
    public String encrypt(String key) {
        String ciphertext = "";

        for (int letter : ciphertextAsNumbers) {
            int shifted = ((letter + key.charAt(0) - 64)) % 26;
            ciphertext += (char) (shifted + (int) 'A');
        }

        return ciphertext;
    }
}
