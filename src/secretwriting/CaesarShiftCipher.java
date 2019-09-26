package secretwriting;

import java.util.List;
import java.util.stream.Collectors;

import helperfoo.Converters;

public class CaesarShiftCipher extends Cipher {
	private String text;
	
	public CaesarShiftCipher(String givenText) {
		text = givenText;
	}
	
	/**
		 * 
		 * @param ciphertext
		 * @param key
		 * @return the plaintext
	 */
	public String decrypt() {
	
		for (int i = 1; i <= 26; i++) {
			final int key = i;
			String shiftedText = this.shiftLetters(key);
		    if (isSentence(shiftedText.split(" "))) {
		    	return shiftedText;
		    }
		}
		return "This ciphertext has non-English plaintext";
	}
	
	/**
	 * 
	 */
	public String encrypt() {
		String ciphertext = this.shiftLetters((int)(Math.random() * 25));
		return ciphertext;
		
	}
	
	/**
	 * 
	 */
	public String magic() {
		
	}
	
	/**
	 * @param key the number of letters to shift to the right from the original letter
	 * @param unshiftedText the ciphertext or the plaintext
	 * @return the shifted output message
	 */
	private String shiftLetters(int key) {
		List<Character> unshiftedChars = Converters.convertStringToListOfCharacters(text);
		
	    List<Character> shiftedText = unshiftedChars.stream()
	      .map( c -> {
	    	  
	    	  if (isSpaceOrPunctuation(c)) {
	    		  return c;
	    	  }
	
	    	  // Shift the letter by an integer, key
	    	  int index = ALPHABET.indexOf(c);
	    	  int newPosition = index + key;
	    	  if (newPosition > 25) {
	    		  newPosition -= 26; 
	    	  }
	    	  
	    	  return ALPHABET.get(newPosition); // Return the new position
	      }).collect(Collectors.toList());
	    
	      return convertListToString(shiftedText);
	}
}
