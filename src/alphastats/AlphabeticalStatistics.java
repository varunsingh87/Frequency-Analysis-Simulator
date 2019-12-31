package alphastats;

import java.util.Arrays;

/**
 * "Static" class for alphabetical statistics: 
 * @author Varun Singh
 *
 */

// Make final class so class cannot be extended
public final class AlphabeticalStatistics {
	public final static String[] DIGRAPHS = {"th", "er", "on", "an", "re", "he", "in", "ed", "nd", "ha", "at", "en", "es", "of", "or", "nt", "ea", "ti", "to", "it", "st", "io", "le", "is", "ou", "ar", "as", "de", "rt", "ve"};
	public final static String[] TRIGRAPHS = {"the", "and", "tha", "ent", "ion", "tio", "for", "nde", "has", "nce", "edt", "tis", "oft", "sth", "men"};
	public final static String[] ONE_LETTER_WORDS = {"a", "i"};
	public final static String[] TWO_LETTER_WORDS = {"of", "to", "in", "it", "is", "be", "as", "at", "so", "we", "he", "by", "or", "on", "do", "if", "me", "my", "up", "an", "go", "no", "us", "am"};
	public final static String[] THREE_LETTER_WORDS = {"the", "and", "for", "are", "but", "not", "you", "all", "any", "can", "had", "her", "was", "one", "our", "out", "day", "get", "has", "him", "his", "how", "man", "new", "now", "old", "see", "two", "way", "who", "boy", "did", "its", "let", "put", "say", "she", "too", "use"};
	public final static String[] FOUR_LETTER_WORDS = {"that", "with", "have", "this", "will", "your", "from", "they", "know", "want", "been", "good", "much", "some", "time"};
	
	public final static String[] DOUBLE_LETTERS = {"ss", "ee", "tt", "ff", "ll", "mm", "oo"};
	
	public final static char[] FINAL_LETTERS = {'E', 'S', 'T', 'D', 'N', 'R', 'Y', 'F', 'L', 'O', 'G', 'H', 'A', 'K', 'M', 'P', 'U', 'W'};
	public final static char[] INITIAL_LETTERS = {'T', 'A', 'O', 'S', 'W', 'H', 'B', 'I', 'C', 'D'};
	public final static char[] ALL_LETTERS = {'E', 'T', 'A', 'O', 'I', 'N', 'S', 'H', 'R', 'D', 'L', 'U'};
	public final static char[] SOCIAL_LETTERS = {'A', 'E', 'I', 'O', 'U'};
	
	private AlphabeticalStatistics() {} // Make private constructor so no instances can be made
	
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
		     if(Arrays.stream(DOUBLE_LETTERS).anyMatch(String.valueOf(letter)::equals) || !plaintext) {
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
	
	public static String doubleLetterInWord(String word) {
		for (int i = 0; i < word.length() - 1; i++) {
			if (word.charAt(i) == word.charAt(i + 1)) {
				return "" + word.charAt(i) + word.charAt(i);
			}
		}
		return "-";
	}
	
	public static boolean needsOneLetter(String word) {
		String res = "";
		for(int i = 0; i < word.length(); i++) {
		   Character ch = word.charAt(i);
		     if(!Character.isUpperCase(ch))
		       res += ch;
		}
		return res.split("[A-Z]").length == 2;
	}
	
	public static boolean meetsAllConditions(Boolean... conditions) {
		return Arrays.stream(conditions).allMatch(c -> true);
	}
	
}
