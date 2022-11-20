package frequencyanalysissimulator.business;

public class Vigenere {
    private String ciphertext;
    private int keylength;

    public Vigenere(int len, String cipher) {
        keylength = len;
        ciphertext = cipher;
    }

    public char decryptCosetByChiSquare(byte coset) {
        double[] frequencies = new double[26];

        for (char letter : ciphertext.toCharArray()) {
            frequencies[Character.toLowerCase(letter) - 'a']++;
        }

        double[] chiSquareValues = new double[25];
        for (int j = 0; j < 25; j++) {
            double chiSquareValue = 0;
            for (int i = 0; i < 26; i++) {
                double standardFrequency = FrequencyAnalysis.standardEnglishFrequencies[i];
                chiSquareValue += Math.pow(frequencies[i] - standardFrequency, 2) / standardFrequency;
            }
            chiSquareValues[j] = chiSquareValue;
        }

        double smallestChiSquareValue = Double.MAX_VALUE;
        char letter = 'A';
        for (int i = 0; i < chiSquareValues.length; i++) {
            if (chiSquareValues[i] < smallestChiSquareValue) {
                smallestChiSquareValue = chiSquareValues[i];
                letter = (char) (i + (int) 'A');
            }
        }

        return letter;
    }
}
