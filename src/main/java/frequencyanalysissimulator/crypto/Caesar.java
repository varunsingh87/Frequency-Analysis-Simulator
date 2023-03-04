package frequencyanalysissimulator.crypto;

public class Caesar implements Cipher {
    private String ciphertext;
    private int[] ciphertextAsNumbers;

    public Caesar(String t) {
        ciphertext = String.join("", t.split("[ \r\t\n]")).toUpperCase();
        ciphertextAsNumbers = new int[t.length()];
    }

    /**
     * Use most common occurence, assume it's e, and shift the rest using this (Kasiski's original
     * method of finding the key)
     * 
     * @return
     */
    public String decryptByKasiski() {
        int[] frequencies = FrequencyAnalysis.calculateAbsoluteLetterFrequencies(ciphertext);
        int maxFreq = 0;
        char mostCommonLetter = 'A';
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > maxFreq)
                maxFreq = frequencies[i];
            mostCommonLetter = (char) (i + 'A');
        }

        return Caesar.encrypt(ciphertext, 27 - (mostCommonLetter - 64));
    }

    /**
     * Decrypts an individual Caesar cipher using Chi Square analysis
     * to find which number of shifts matches the standard English frequency
     * distribution the most
     * AKA Kerckhoff's Method
     * 
     * @time O(n) - Increases linearly with the length of the ciphertext
     * 
     * @param ciphertext
     *            The Caesar cipher text to get the letter rotation of
     * @return The letter that represents the number of rotations for the Caesar
     *         cipher and part of the key for the Vigenere cipher
     */
    public char getKeyByChiSquare() {
        double[] ciphertextLetterFrequencies = new double[26];
        // Populate ciphertext letter frequencies by adding one for every occurrence of
        // letter O(n)
        for (int i = 0; i < ciphertext.length(); i++) {
            char letter = ciphertext.charAt(i);
            int asNum = letter - 65;
            ciphertextAsNumbers[i] = asNum;
            // Ignore anything other than letters (such as spaces)
            if (Character.isAlphabetic((int) letter)) {
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
        int keyLetAsNum = 27 - (keyLet - 64);
        return Caesar.encrypt(ciphertext, keyLetAsNum);
    }

    /**
     * Encrypts a text with the key where A = key with Casar Shift
     * 
     * @apiNote A = 0, so encrypt('A') = encrypt(1 - 1) = encrypt(0)
     * 
     * @param key
     *            Precondition: A-Z, one letter long
     *            Postcondition: Encrypted Caesar ciphertext
     * @see frequencyanalysissimulator.crypto.Cipher#encrypt(java.lang.String)
     */
    public static String encrypt(String plaintext, String key) {
        return encrypt(plaintext, (int) (key.charAt(0) - 64 - 1));
    }

    /**
     * Precondition: 0 <= key <= 26
     * 
     * @apiNote Equals this.inputText when key is 0 or 26
     * @param key
     *            The shift
     * @return The encrypted ciphertext
     */
    public static String encrypt(String plaintext, int key) {
        String ciphertext = "";

        for (char letter : plaintext.toCharArray()) {
            /*
             * Subtract 64 - letters have ASCII codes starting at 65
             * Add shift
             * Subtract 1 - Otherwise a shift of 26 would become 0 when modulo is performed
             * Stay within 0-25 range - later letters in the alphabet would otherwise go
             * above
             * Add 1 - Make that range 1 - 26
             */
            int shifted = (letter - 64 + key - 1) % 26 + 1;
            ciphertext += (char) (shifted + 'A' - 1);
        }

        return ciphertext;
    }

    public float calculateIndexOfCoincidence() {
        float indexOfCoincidence = 0;

        int[] ciphertextLetterCounts = FrequencyAnalysis.calculateAbsoluteLetterFrequencies(ciphertext);

        for (int i = 0; i < 26; i++) {
            double countTimesCountMinusOne = ciphertextLetterCounts[i] * (ciphertextLetterCounts[i] - 1);
            indexOfCoincidence += countTimesCountMinusOne;
        }

        indexOfCoincidence /= ciphertext.length() * (ciphertext.length() - 1);

        return indexOfCoincidence;
    }
}
