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
        return this.encrypt(keyLetAsNum);
    }

    /**
     * Encrypts a text with the key where A = key with Casar Shift
     * 
     * @apiNote A = 0, so encrypt('A') = encrypt(1 - 1) = encrypt(0)
     * 
     * @param key Precondition: A-Z, one letter long
     *            Postcondition: Encrypted Caesar ciphertext
     * @see frequencyanalysissimulator.business.Cipher#encrypt(java.lang.String)
     */
    @Override
    public String encrypt(String key) {
        return encrypt((int) (key.charAt(0) - 64 - 1));
    }

    /**
     * Precondition: 0 <= key <= 26
     * 
     * @apiNote Equals this.inputText when key is 0 or 26
     * @param key The shift
     * @return The encrypted ciphertext
     */
    public String encrypt(int key) {
        String ciphertext = "";

        for (char letter : inputText.toCharArray()) {
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
}
