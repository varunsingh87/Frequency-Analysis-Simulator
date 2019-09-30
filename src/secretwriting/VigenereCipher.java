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
			this.encrypt(key.getLemma());
		} catch (JWNLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String encrypt(String key) {
		
		for (char keyChar : key.toCharArray()) {
			// n in the nth letter in the entire key
			int indexOfKey = key.indexOf(keyChar); 
			// n in the nth letter in the alphabet which represents the current letter in the key
			int keyInSeries = EnglishDeterminer.ALPHABET.indexOf(keyChar) + 1; 
			
			String cipherSubset = new CaesarShiftCipher(getText()).shiftLetters(keyInSeries);
			System.out.println(cipherSubset + "\n\n");
			for (int i = indexOfKey; i < getText().length(); i+=key.length()) {
				char charToReplace = getText().charAt(i);
				char charThatReplaces = cipherSubset.charAt(i);
				setText(getText().replace(charToReplace, charThatReplaces));
			}
		}
		return getText();
		
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
