package alphastats;

import java.util.Arrays;

/**
 * class for alphabetical stats
 * @author Varun Singh
 *
 */

// Make final class so class cannot be extended
public final class AlphabeticalStatistics {
	public static char[] DOUBLE_LETTERS = {'S', 'E', 'T', 'F', 'L', 'M', 'O'};
	
	// Make private constructor so no instances can be made
	private AlphabeticalStatistics() {
		
	}
	
	/**
	 * Checks if a word has double letters and returns a boolean indictating this
	 * @param text the string of text that the operation is performed upon
	 * @param letter the letter that is checked for two-in-a-row repetition
	 * @param plaintext whether or not the letter and text is in plaintext
	 * @return TRUE if the word has the specified double letter and FALSE if it does not
	 * @throws IllegalArgumentException custom exception if the letter passed in is not possible to be in double letters in the English language
	 * @exception IllegalArgumentException when caught, it returns false
	 */
	public static boolean hasDoubleLetters(String text, char letter, boolean plaintext) {
		
		 try {
		 
			 // Check if the letter can be a bigram
		     if(new String(DOUBLE_LETTERS).contains(String.valueOf(letter)) || !plaintext)
		     {
		    	 // If so, check if the text has the given letter as a bigram
		    	 return text.indexOf("" + letter + letter) != -1;
  		     }
		     
		     throw new IllegalArgumentException("The letter is not valid to be checked for doubling because it can never be doubled. ");
		}
		catch(IllegalArgumentException ex)
		{
		    // Process message and return default value
			System.out.println(ex.getMessage());
			return false;
		}
		
	}
	
	public static boolean isNLetters(String word, int n) {
		return word.length() == n;
	}
	
	public static boolean hasDoubleInWord(String word) {
		return word.matches(".*([A-Z])\\1.*");
	}
	
	public static char doubleLetterInWord(String word) {
		for (char letter : DOUBLE_LETTERS) {
			if (word.contains("" + letter + letter)) {
				
				return letter;
			}
		}
		
		return '-';
		
	}
	
	public static boolean meetsAllConditions(Boolean... conditions) {
		System.out.println(Arrays.asList(Arrays.toString(conditions)));
		return !Arrays.asList(conditions).contains(false);
	}
}
