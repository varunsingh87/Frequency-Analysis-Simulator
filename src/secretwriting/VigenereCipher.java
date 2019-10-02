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
		return getText(); 
	}

	public String encrypt() {
		String result = "";
		try {
			Dictionary d = Dictionary.getDefaultResourceInstance();	
			IndexWord key = d.getRandomIndexWord(POS.NOUN);
			while (key.getLemma().contains(" ") || key.getLemma().contains("-"))
				key = d.getRandomIndexWord(POS.NOUN);
			result = this.encrypt(key.getLemma());
		} catch (JWNLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String encrypt(String key) {
		final String textWithoutSpaces = getText().replace(" ", "").replace("3", "").replace("4", "").replace(".", "").replace(",", ""); // Removes spaces for accuracy of encryption
		StringBuilder mutatableText = new StringBuilder(textWithoutSpaces);
	
		System.out.println(key);
		
		for (char keyChar : key.toCharArray()) {
			// index of keyChar in the keyword (key)
			int indexInKey = key.indexOf(keyChar); 
			
			// index of keyChar in the English alphabet
			int indexInAlphabet = EnglishDeterminer.ALPHABET.indexOf(keyChar); 
			String cipherSubset = new CaesarShiftCipher(textWithoutSpaces).shiftLetters(indexInAlphabet);
			System.out.println("\n" + cipherSubset);
			/*
			 * Loop through the text
			 * Starting at the index of keyChar in key
			 * Incrementing by the length of the key
			 */
			for (int i = indexInKey; i < textWithoutSpaces.length(); i+=key.length()) {
				// Replace the current letter with the letter in the current cipher subset
				mutatableText.setCharAt(i, cipherSubset.charAt(i));
			}
		}
		
		return mutatableText.toString();	
	}
	
	public static String magic() {
		return new VigenereCipher("In the 34 years since the R.M.S. Titanic was discovered on the seafloor south of Newfoundland, it has become the world’s most famous shipwreck — a rusting hulk assailed by hundreds of explorers and moviemakers, salvors and tourists, scientists and federal watchdogs. All agree that the once-grand ship is rapidly falling apart. Resting on the icy North Atlantic seabed more than two miles down, upright but split in two, the fragile mass is slowly succumbing to rust, corrosive salts, microbes and colonies of deep-sea creatures.").encrypt();
	}
	
	public ArrayList<Character> generateCipherAlphabet() {
		return null;
		
	}

	public void printCipherAlphabetAsTable() {
		System.out.println("");
	}

}
