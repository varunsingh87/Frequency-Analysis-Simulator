package frequencyanalysissimulator.crypto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Vigenere {
    private String cipherText;
    private String letterOnlyCipherText;
    private int keylength;
    private KeyLengthMethod method;

    public Vigenere(String cipher, KeyLengthMethod m) {
        cipherText = cipher.replaceAll("\\s+", " ");
        letterOnlyCipherText = removeNonLetters();
        method = m;
        if (m.equals(KeyLengthMethod.IOC)) {
            keylength = this.calculateKeyLengthByIndexOfCoincidence();
        } else if (m.equals(KeyLengthMethod.FRIEDMAN)) {
            keylength = this.calculateKeyLengthByFriedmanTest();
        }
    }

    public Vigenere(int keylen, String cipher) {
        setCipherText(cipher);
        keylength = keylen;
    }

    public Vigenere(String cipher) {
        setCipherText(cipher);
        keylength = this.calculateKeyLengthByIndexOfCoincidence();
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String newCipher) {
        cipherText = newCipher.replaceAll("\\s+", " ");
        letterOnlyCipherText = removeNonLetters();
    }

    @Override
    public String toString() {
        return String.format("ciphertext: %s\nkeylength method: %s", cipherText, method.name());
    }

    public String removeNonLetters() {
        return cipherText.replaceAll("[^A-Za-z]", "").toUpperCase();
    }

    private List<Integer> markNonLetters() {
        ArrayList<Integer> nonLetters = new ArrayList<>();
        for (int i = 0; i < cipherText.length(); i++) {
            // Add nonletters, but only the first space in a string of spaces
            if (!Character.isLetter(cipherText.charAt(i))) {
                // System.out.println("Storing " + cipherText.charAt(i) + " with index " + i + " for later");
                nonLetters.add(i);
            }
        }
        return nonLetters;
    }

    private String[] distributeCiphertextIntoCosets() {
        try {
            String[] cosets = new String[keylength];
            Arrays.fill(cosets, "");

            for (int i = 0; i < letterOnlyCipherText.length(); i++) {
                cosets[i % keylength] += letterOnlyCipherText.charAt(i);
            }

            return cosets;
        } catch (NegativeArraySizeException e) {
            System.out.println("Invalid key length. Algorithm failed.");
            System.exit(0);
            return null;
        }
    }

    /**
     * Use coincidence counting (Kasiski Examination) to deduce the key length
     * 
     * @return The most probable key length using this particular algorithm
     */
    int calculateKeyLengthByIndexOfCoincidence() {
        float maxAvg = 0;
        int mostProbableKeyLength = 3;
        for (int i = 2; i <= 20; i++) {
            float currentAvg = calculuateAvgIndexOfCoincidence(i);
            if (currentAvg > maxAvg) {
                maxAvg = currentAvg;
                mostProbableKeyLength = i;
            }
        }

        return mostProbableKeyLength;

    }

    int calculateKeyLengthByKasiskiExamination() {
        Map<String, Integer> ngrams = new HashMap<>();
        Map<String, Integer> repeatedNGrams = new HashMap<>();
        Map<Integer, Integer> possibleKeyLengths = new TreeMap<>();

        // Removes the ngrams used in the ciphertext as loop proceeds
        StringBuilder adjustedCipherText = new StringBuilder(letterOnlyCipherText);

        // O(n)
        for (int n = 4; n >= 3; n--) {
            for (int i = 0; i < adjustedCipherText.length() - (n - 1); i++) {
                String ngram = adjustedCipherText.substring(i, i + n);

                if (ngrams.containsKey(ngram)) {
                    int prevNGramDistance = ngrams.get(ngram);
                    int distance = i - prevNGramDistance;

                    ngrams.replace(ngram, i);
                    repeatedNGrams.put(ngram, distance);
                } else {
                    ngrams.put(ngram, i);
                }
            }
        }

        /*
         * Add and count up the factors of each trigram's distance and store in a map
         * Efficiency: O(n^0.5)
         */
        repeatedNGrams.forEach((trigram, distance) -> {
            System.out.printf("%-15s %-15d\n", trigram, distance);
            for (int i = 2; i < Math.sqrt(distance); i++) {
                if (distance % i == 0) {
                    if (possibleKeyLengths.containsKey(i))
                        possibleKeyLengths.put(i, possibleKeyLengths.get(i) + 1);
                    else
                        possibleKeyLengths.put(i, 1);
                }
            }
        });

        System.out.println(possibleKeyLengths);

        int maxRepeats = 0;
        int mostProbableKeyLength = 1;
        for (Map.Entry<Integer, Integer> factor : possibleKeyLengths.entrySet()) {
            if (factor.getValue() >= maxRepeats) {
                mostProbableKeyLength = factor.getKey();
                maxRepeats = factor.getValue();
            }
        }

        System.out.println(maxRepeats);

        return mostProbableKeyLength;
    }

    /**
     * Friedman's Test on a possible key length
     * I=sum(letter frequency * (letter frequency - 1)) / (Length of text * (Length
     * of text - 1))
     * Calculates the probability of two randomly picked symbols in a text to be equal
     *
     * @return I, the coincidence index
     */
    float calculateIndexOfCoincidence() {
        float indexOfCoincidence = 0;

        int[] ciphertextLetterCounts = FrequencyAnalysis.calculateAbsoluteLetterFrequencies(letterOnlyCipherText);

        for (int i = 0; i < 26; i++) {
            double countTimesCountMinusOne = ciphertextLetterCounts[i] * (ciphertextLetterCounts[i] - 1);
            indexOfCoincidence += countTimesCountMinusOne;
        }

        indexOfCoincidence /= letterOnlyCipherText.length() * (letterOnlyCipherText.length() - 1);

        return indexOfCoincidence;
    }

    float calculuateAvgIndexOfCoincidence(int keyLen) {
        String[] cosets = new Vigenere(keyLen, cipherText).distributeCiphertextIntoCosets();
        float avg = 0f;
        for (String coset : cosets) {
            avg += new Caesar(coset).calculateIndexOfCoincidence();
        }
        avg /= keyLen;
        return avg;
    }

    int calculateKeyLengthByFriedmanTest() {
        final double KAPPA_R = 0.0385; // Arabic alphabet
        final double KAPPA_P = 0.067; // case-insensitive English
        double ioc = this.calculateIndexOfCoincidence();

        return Math.max(1, (int) Math.round((KAPPA_P - KAPPA_R) / (ioc - KAPPA_R)));
    }

    public float getCipherKeyLenRatio() {
        return cipherText.length() / keylength;
    }

    boolean isPolyalphabetic() {
        double ioc = this.calculateIndexOfCoincidence();
        return ioc >= 0.0661 && ioc <= 0.065;
    }

    public String getKey() {
        StringBuilder key = new StringBuilder(); // A String
        String[] cosets = this.distributeCiphertextIntoCosets();

        for (int i = 0; i < keylength; i++) {
            Caesar coset = new Caesar(cosets[i]);
            key.append(coset.getKeyByChiSquare());
        }

        return key.toString();
    }

    public String decrypt(CaesarDecryptionMethod keyAlg) {
        StringBuilder plaintext = new StringBuilder("");
        String[] cosets = this.distributeCiphertextIntoCosets();

        for (int i = 0; i < keylength; i++) {
            Caesar coset = new Caesar(cosets[i]);
            cosets[i] = coset.decrypt(keyAlg);
        }

        for (int i = 0; i < letterOnlyCipherText.length(); i++) {
            plaintext.append(cosets[i % keylength].charAt((int) Math.ceil(i / keylength)));
        }

        List<Integer> nonLetterLocations = markNonLetters();

        for (int i = 0; i < nonLetterLocations.size(); i++) {
            int index = nonLetterLocations.get(i);
            plaintext.insert(index, cipherText.charAt(index));
        }

        return plaintext.toString();
    }

    public String decrypt() {
        return decrypt(null);
    }

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder("");
        ArrayList<Integer> nonLetters = new ArrayList<>();
        for (int i = 0; i < plaintext.length(); i++) {
            // Add nonletters, but only the first space in a string of spaces
            if (!Character.isLetter(plaintext.charAt(i))) {
                // System.out.println("Storing " + cipherText.charAt(i) + " with index " + i + " for later");
                nonLetters.add(i);
            }
        }

        String replacedPlaintext = plaintext.replaceAll("[^A-Za-z]", "").toUpperCase();

        for (int i = 0; i < replacedPlaintext.length(); i++) {
            char letter = Character.toUpperCase(replacedPlaintext.charAt(i));

            // Subtract 65 from each character to get the nth letter of the alphabet
            // Confine to 0-25 with % 26
            int shifted = (letter - 65 + key.charAt(i % key.length()) - 65) % 26;
            // Add back 65 when turning to a character (does not need -1 because we did not
            // add 1 above)
            ciphertext.append((char) (shifted + 'A'));
        }

        for (int i = 0; i < nonLetters.size(); i++) {
            int index = nonLetters.get(i);
            // System.out.print("\r" + plaintext.toString());
            // System.out.println("Inserting " + cipherText.charAt(index) + " at index " + index);
            // System.out.println("New length of plaintext: " + plaintext.length());
            ciphertext.insert(index, plaintext.charAt(index));
        }

        return ciphertext.toString();
    }
}
