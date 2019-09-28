package secretwriting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import helperfoo.Converters;
import helperfoo.EnglishDeterminer;

public class MonoalphabeticCipher extends Cipher {
	
	public MonoalphabeticCipher(String givenText) {
		super(givenText);
	}
	
	/** Creates a cipher alphabet
	 * @return the cipher alphabet - the shuffled plaintext alphabet
	 */
	public ArrayList<Character> generateCipherAlphabet() {
		ArrayList<Character> cipheralphabet = new ArrayList<Character>();
		for (char letter : EnglishDeterminer.ALPHABET) {
		    cipheralphabet.add(letter);
		}
		
		Collections.shuffle(cipheralphabet);
		
		return cipheralphabet;
	}

	public String decrypt() {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Encrypts a message into a monoalphabetic cipher
	 * @param plaintext
	 * @return
	 */
	public String encrypt() {
		List<Character> plaintextchars = Converters.convertStringToListOfCharacters(text);
		List<Character> cipherAlphabet = generateCipherAlphabet();
		List<Character> plainAlphabet = EnglishDeterminer.ALPHABET;
		
		List<Character> cipherchars = plaintextchars.stream().map(p -> {
			if (EnglishDeterminer.isSpaceOrPunctuation(p)) {
				return p;
			}
			
			int nthLetter = plainAlphabet.indexOf(p);
			
			return cipherAlphabet.get(nthLetter);
		}).collect(Collectors.toList());	
		
		return Converters.convertListToString(cipherchars);
	}

	public String magic() {
		// TODO Auto-generated method stub
		return null;
	}

	public void printCipherAlphabetAsTable() {
		// TODO Auto-generated method stub
		
	}

}
