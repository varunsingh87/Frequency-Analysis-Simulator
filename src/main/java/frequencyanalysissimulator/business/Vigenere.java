package frequencyanalysissimulator.business;

import java.util.Arrays;

public class Vigenere implements Cipher {
    private String ciphertext;
    private int keylength;

    public Vigenere(int len, String cipher) {
        keylength = len;
        ciphertext = String.join("", cipher.split(" ")); // Remove spaces
    }

    private String[] distributeCiphertextIntoCosets() {
        String[] cosets = new String[keylength];
        Arrays.fill(cosets, "");
        for (int i = 0; i < ciphertext.length(); i++) {
            cosets[i % keylength] += ciphertext.charAt(i);
        }
        return cosets;
    }

    public String decrypt() {
        // TODO Replace using key
        return null;
    }

    @Override
    public CharSequence getKey() {
        String key = "";
        String[] cosets = this.distributeCiphertextIntoCosets();

        for (int i = 0; i < keylength; i++) {
            Caesar coset = new Caesar(cosets[i]);
            key += coset.getKeyByChiSquare();
        }

        System.out.println("Key is " + key);
        return key;
    }

    @Override
    public String encrypt() {
        // TODO Auto-generated method stub
        return null;
    }
}
