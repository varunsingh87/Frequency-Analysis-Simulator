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

	/**
	 * Save the skipped characters and their indexes to an array list of array lists
	 * using isSpaceOfPunctuation() and an enhanced for loop,
	 * taking no parameters
	 * @return a 2-D dimensional array of the skipped indexes and the skipped elements where
	 * the index is the first index
	 * and the element is the second index
	 */
	private ArrayList<ArrayList<Object>> saveSkipped() {
		ArrayList<ArrayList<Object>> skipped = new ArrayList<ArrayList<Object>>();
		/*
		 * Loop through the text looking for spaces and punctuation
		 * Save the character and letter in a 2-D ArrayList
		 * Save both as strings
		 * Adds this list as a nested list to skipped array list
		*/
		for (int i = 0; i < length(); i++) {
			char el = getText().charAt(i);
			if (EnglishDeterminer.isSpaceOrPunctuation(el) || EnglishDeterminer.isInteger(String.valueOf(el))) {
				int ind = i;
				char newEl = el;
				
				ArrayList<Object> list = new ArrayList<Object>();

				list.add(ind); // Add the index
				list.add(newEl); // Add the element
				skipped.add(list); // Add the list
			}
		}
		
		return skipped;
		
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
		final String textWithoutSpaces = getText().replace(" ", "").replace("3", "").replace("4", "").replace(".", "").replace(",", "").replace("—", "").replace("’", "").replace("-", ""); // Removes spaces for accuracy of encryption
		StringBuilder mutatableText = new StringBuilder(textWithoutSpaces); // Get String as StringBuilder for mutation
	
		System.out.println(key);
		
		for (int i = 0; i < key.length(); i++) {
			char keyChar = key.charAt(i);
			
			// index of keyChar in the English alphabet
			int indexInAlphabet = EnglishDeterminer.ALPHABET.indexOf(keyChar); 
			String cipherSubset = new CaesarShiftCipher(textWithoutSpaces).shiftLetters(indexInAlphabet);

			/*
			 * Loop through the text
			 * Starting at the index of keyChar in key
			 * Incrementing by the length of the key
			 */
			for (int j = i; j < textWithoutSpaces.length(); j+=key.length()) {
				// Replace the current letter with the letter in the current cipher subset
				mutatableText.setCharAt(j, cipherSubset.charAt(j));
			}
		}
		
		for (ArrayList<Object> skipped : saveSkipped()) {
			mutatableText = mutatableText.insert((int) skipped.get(0), skipped.get(1));
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
