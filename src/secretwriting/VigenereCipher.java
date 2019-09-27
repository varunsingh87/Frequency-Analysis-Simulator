package secretwriting;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VigenereCipher extends Cipher {
	
	public VigenereCipher(String givenText) {
		super(givenText);
	}

	public String decrypt() {
		// TODO Implement decipherVigenere(String ciphertext) method
		Pattern p = Pattern.compile(".*(.+).*\1.*");
		Matcher m = p.matcher(getText());
		Boolean b = m.matches();
		System.out.println(b);
		String plaintext = new MonoalphabeticCipher(getText()).decrypt();
		return plaintext; 
	}

	public String encrypt() {
		// TODO Auto-generated method stub
		return null;
	}

	public String magic() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Character> generateCipherAlphabet() {
		return null;
		
	}

	@Override
	public void printCipherAlphabetAsTable() {
		// TODO Auto-generated method stub
		
	}

}
