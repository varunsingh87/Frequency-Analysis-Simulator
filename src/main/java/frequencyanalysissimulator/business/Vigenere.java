package frequencyanalysissimulator.business;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class Vigenere implements Cipher {
    private String inputText;
    private char[] inputChars;
    private int keylength;

    public Vigenere(int len, String cipher) {
        keylength = len;
        // Remove anything that is not a letter
        inputText = cipher.replaceAll("[^A-Za-z]", "").toUpperCase();
        inputChars = inputText.toCharArray();
    }

    public Vigenere(String plain) {
        inputText = plain.replaceAll("[^A-Za-z]", "").toUpperCase();
        inputChars = inputText.toCharArray();
    }

    private String[] distributeCiphertextIntoCosets() {
        String[] cosets = new String[keylength];
        Arrays.fill(cosets, "");
        for (int i = 0; i < inputText.length(); i++) {
            cosets[i % keylength] += inputText.charAt(i);
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
        for (int i = 0; i < inputText.length() - 2; i++) {
            String trigram = inputText.substring(i, i + 2);

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
    private double calculateIndexOfCoincidence(int length) {
        double sum = 0;

        for (int i = 0; i < 25; i++) {
            // sum +=
        }

        sum /= inputText.length() * (inputText.length() - 1);

        return sum;
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

        for (int i = 0; i < inputText.length(); i++) {
            plaintext += cosets[i % keylength].charAt((int) Math.ceil(i / keylength));
        }

        return plaintext;
    }

    @Override
    public String encrypt(String key) {
        String ciphertext = "";

        for (int i = 0; i < inputText.length(); i++) {
            // Subtract 65 from each character to get the nth letter of the alphabet
            // Confine to 0-25 with % 26
            int shifted = (inputText.charAt(i) - 65 + key.charAt(i % key.length()) - 65) % 26;
            // Add back 65 when turning to a character (does not need -1 because we did not
            // add 1 above)
            ciphertext += (char) (shifted + 'A');
        }

        return ciphertext;
    }
}
