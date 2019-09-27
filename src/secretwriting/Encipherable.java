package secretwriting;

import java.util.ArrayList;

/**
 * 
 * @author Varun S
 *
 */
public interface Encipherable {
	public ArrayList<Character> generateCipherAlphabet();
	
	public void printCipherAlphabetAsTable();
}
