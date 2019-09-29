package secretwriting;

import java.util.ArrayList;

import helperfoo.EnglishDeterminer;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

public class VigenereCipher extends Cipher {
	
	public VigenereCipher(String givenText) {
		super(givenText);
	}

	public String decrypt() {
		return text; 
	}

	public String encrypt() {
		try {
			Dictionary d = Dictionary.getDefaultResourceInstance();	
			IndexWord key = d.getRandomIndexWord(POS.NOUN);
			this.encrypt(key.toString());
		} catch (JWNLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String encrypt(String key) {
		String encryptedText = "";
		for (char keyChar : key.toCharArray()) {
			// n in the nth letter in the entire key where
			int indexOfKey = key.indexOf(keyChar); 
			// n in the nth letter in the alphabet which represents the current letter in the key
			int keyInSeries = EnglishDeterminer.ALPHABET.indexOf(keyChar) + 1; 
			String cipherSubset = new CaesarShiftCipher("").shiftLetters(keyInSeries);
			for (int i = 0; i < text.length(); i++) {
				encryptedText = text.replace(text.charAt(i * key.length() + indexOfKey), cipherSubset.charAt(i));
			}
		}
		return encryptedText;
		
	}

	public String magic() {
		return null;
	}
	
	public ArrayList<Character> generateCipherAlphabet() {
		return null;
		
	}

	public void printCipherAlphabetAsTable() {
		System.out.println("");
	}

}
