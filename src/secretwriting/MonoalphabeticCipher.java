package secretwriting;

import java.util.ArrayList;
import java.util.Collections;
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

	public String encrypt() {
		// TODO Auto-generated method stub
		return null;
	}

	public String magic() {
		// TODO Auto-generated method stub
		return null;
	}

	public void printCipherAlphabetAsTable() {
		// TODO Auto-generated method stub
		
	}

}
