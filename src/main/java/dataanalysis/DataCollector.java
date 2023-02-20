package dataanalysis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import frequencyanalysissimulator.crypto.Vigenere;

public class DataCollector {

    /**
     * 
     * @param args
     *            arg[0]: Input must be one line
     *            arg[1]: Trial Id for output file
     *            arg[2]: (Optional): key
     */
    public static void main(String[] args) {
        final String key = args.length > 2 ? args[2] : "DONQUIXOTECOYOTEWILL";
        String expectedText = args[0].toUpperCase();
        String output = String.format("Len,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,Avg(%s)\n", args[1]);

        for (int cipherlen = 90; cipherlen <= 1400; cipherlen += 50) { // O(15) = O(C)
            output += cipherlen + ",";
            double avg = 0;
            for (int i = 3; i <= 20; i++) { // O(17) = O(C)
                String input = expectedText.substring(0, cipherlen);
                String subKey = key.substring(0, i);
                String ciphertext = Vigenere.encrypt(input, subKey);
                Vigenere v = new Vigenere(ciphertext);
                String decryptedText = v.decrypt();
                double accuracy = percentageSimilarity(decryptedText, input);

                output += Math.round(accuracy) + ",";
                avg += accuracy;
            }

            output += Math.round(avg / 18.0) + "\n";
        }

        try (FileWriter writer = new FileWriter(
                new File(String.format("data/generatedOutputs/Trial %s.csv", args[1])))) {
            writer.append(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Preconditions:
     * (1) input1 is the same exact size as input2 (input1.length() == input2.length())
     * (2) input1 and input2 have all the non-letters in the same exact places
     * 
     * @return Ratio of exact matches to input size as a percentage
     * @throws IndexOutOfBoundsException
     *             If input1.length() !== input2.length()
     */
    static float percentageSimilarity(String input1, String input2) {
        float count = 0;
        for (int i = 0; i < input1.length(); i++) {
            if (input1.charAt(i) == input2.charAt(i)) {
                count += 1.0 / input1.length();
            }
        }

        return count * 100;
    }
}
