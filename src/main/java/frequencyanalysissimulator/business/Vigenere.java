package frequencyanalysissimulator.business;

import java.util.Arrays;

public class Vigenere implements Cipher {
    private String inputText;
    private int keylength;

    public Vigenere(int len, String cipher) {
        keylength = len;
        // Remove spaces and carriage returns
        inputText = String.join("", cipher.split("[ \r\t\n]")).toUpperCase();
    }

    private String[] distributeCiphertextIntoCosets() {
        String[] cosets = new String[keylength];
        Arrays.fill(cosets, "");
        for (int i = 0; i < inputText.length(); i++) {
            cosets[i % keylength] += inputText.charAt(i);
        }
        return cosets;
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
        // TODO Auto-generated method stub
        return null;
    }
}
