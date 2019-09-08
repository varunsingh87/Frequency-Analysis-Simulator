/**
 * Frequency Analysis Simulator
 * By Varun Singh
 */

/**
 * @author Varun Singh
 * @inspiration The Code Book by Simon Singh
 * @citations
 	* Princeton University "About WordNet." WordNet. Princeton University. 2010. 
 	* Stack Overflow, Inc.
 */

/*
OF MIT 34 BTAKL LOFET MIT K.D.L. MOMAFOE CAL ROLEGXTKTR GF MIT LTAYSGGK LGWMI GY FTCYGWFRSAFR, OM IAL ZTEGDT MIT CGKSR'L DGLM YADGWL LIOHCKTEQ -- A KWLMOFU IWSQ ALLAOSTR ZB IWFRKTRL GY TVHSGKTKL AFR DGXOTDAQTKL, LASXGKL AFR MGWKOLML, LEOTFMOLML AFR YTRTKAS CAMEIRGUL. ASS AUKTT MIAM MIT GFET-UKAFR LIOH OL KAHORSB YASSOFU AHAKM. KTLMOFU GF MIT OEB FGKMI AMSAFMOE LTAZTR DGKT MIAF MCG DOSTL RGCF, WHKOUIM ZWM LHSOM OF MCG, MIT YKAUOST DALL OL LSGCSB LWEEWDZOFU MG KWLM, EGKKGLOXT LASML, DOEKGZTL AFR EGSGFOTL GY RTTH-LTA EKTAMWKTL.
*/

package net.sf.extjwnl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

public class FrequencyAnalysisSimulator {
	
	static Scanner userInput = new Scanner (System.in);
	static enum ACTION {
		ENCRYPT,
		DECRYPT
	}
	static List<Character> ALPHABET = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	static char[] charsToSkip = { ' ', '!', '.', '?', ',', ';', '\'', '"', '(', ')', '[', ']', '{', '}'};
	static Exception InvalidInputException = new Exception("You did not enter valid input. Please rerun the program and try again");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws JWNLException {
		ACTION action = determineAction();
		if (action.equals(ACTION.DECRYPT))
			handleDecrypt();
		else if (action.equals(ACTION.ENCRYPT))
			handleEncrypt();
	}
	
	/**
	 * @return whether to encrypt or decipher
	 */
	public static ACTION determineAction() {
		System.out.println("Would you like to encrypt or decrypt?");
		String myString = userInput.nextLine();
		ACTION determinedAction;
		try {
			determinedAction = ACTION.valueOf(myString.toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("That is not a valid response. The default encrypt is being used.");
			determinedAction = ACTION.ENCRYPT;
		}
		return determinedAction;
	}
	
	/** Obtain the cipher type from the user
	 * @param action the action that the user chose to take
	 * @return the type of substitution cipher
	 */
	public static String determineCipherType(ACTION action) {
		// DONE Implement determineCipherType()
		if (action.equals(ACTION.DECRYPT)) {
			System.out.println("If you know the type of cipher this ciphertext is, enter it now. Otherwise, enter a new line.");
		} else if (action.equals(ACTION.ENCRYPT)) {
			System.out.println("What type of cipher would you like to use to encrypt the message? (Caesar shift, monoalphabetic, Vigenere");
		}
		
		String cipherType = userInput.nextLine();
		return cipherType;
	}
	
	/** 
	 * @return the cipher text that the user inputs as a string
	 */
	public static String determineCiphertext() {
		// DONE Implement determineCiphertext() method 
		System.out.println("Enter a ciphertext: ");
		String ciphertext = userInput.nextLine();
		return ciphertext;
		
	}
	
	/** Obtain the plain text message from the user 
	 * @return
	 */
	public static String determinePlaintext() {
		// TODO Implement determinePlaintext() method
		System.out.print("Enter a plaintext: ");
		String plaintext = userInput.nextLine();
		return plaintext;
	}
	
	/** Handles all the methods, tasks, and processes if the user chooses to decipher a message
	 * @throws JWNLException
	 */
	protected static void handleDecrypt() throws JWNLException {
		String ciphertext = determineCiphertext();
		String ciphertype = determineCipherType(ACTION.DECRYPT);
		if (ciphertype.equalsIgnoreCase("Caesar shift")) {
			System.out.println(decipherCaesarShift(ciphertext));
		} else if (ciphertype.equals("monoalphabetic")) {
			System.out.println(decipherMonoalphabetic(ciphertext));
		} else if (ciphertype.equals("Vigenere")) {
			System.out.println(decipherVigenere(ciphertext));
		}
	}
	
	/** Handles all the methods, tasks, and processes if the user chooses to encrypt a message
	 * 
	 */
	protected static void handleEncrypt() {
		String plaintext = determinePlaintext();
		String ciphertype = determineCipherType(ACTION.ENCRYPT);
		if (ciphertype.equalsIgnoreCase("Caesar shift")) {
			System.out.println(encryptCaesarShift(plaintext));
		} else if (ciphertype.equalsIgnoreCase("monoalphabetic")) {
			System.out.println(encryptMonoalphabetic(plaintext));
		}
	}
	
	/**
	 * Returns whether or not a given character is a space or a punctuation mark
	 * @param c the character being checked
	 * @return true if a character is either a space or a punctuation
	 * false if the character is anything else
	 */
	private static boolean isSpaceOrPunctuation(Character c) {
		for (char item : charsToSkip) {
			if (c.equals(item)) {
				return true; // No need to look further.
			} 
		}
		
		return false;
	}
	
	/**
	 * @param key the number of letters to shift to the right from the original letter
	 * @param unshiftedText the ciphertext or the plaintext
	 * @return the shifted output message
	 */
	private static String shiftLetters(final int key, String unshiftedText) {
		// DONE Implement shiftLetters(final int key, String unshiftedText)
		
		List<Character> unshiftedChars = convertStringToListOfCharacters(unshiftedText);
		
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

	/** Determines if a string is in the extended Java WordNet Library dictionary and has the correct part of speech
	 * @param pos
	 * @param word
	 * @return whether the given string is an English word
	 * @throws JWNLException
	 */
	private static boolean isWord(String word) throws JWNLException {
		// DONE Implement isWord(String word) method
		Dictionary d = Dictionary.getDefaultResourceInstance();
		
		Collection<POS> POSList = EnumSet.allOf(POS.class);
		
		List<String> reflexivepronouns = convertStringToListOfStrings("myself yourself herself himself itself ourselves yourselves themselves");
		List<String> outliers = convertStringToListOfStrings("the this that of these those and you for");
		List<String> combinedOutliers = Stream.of(reflexivepronouns, outliers)
	            .flatMap(x -> x.stream())
	            .collect(Collectors.toList());
		boolean isWord = POSList.stream().anyMatch(c -> {
			try {
				return d.lookupIndexWord(c, word) != null;
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		});
		
		return isWord || combinedOutliers.contains(word);
	}

	/**
	 * 
	 * @param words
	 * @return
	 * @throws JWNLException
	 */
	private static boolean isSentence(String[] words) throws JWNLException {
		for (String word : words) {
			if (!isWord(word))
				return false;
		}
		
		return true;
	}

	/**
	 * 
	 * @param ciphertext
	 * @param key
	 * @return the plaintext
	 */
	public static String decipherCaesarShift(String ciphertext) throws JWNLException {
		// DONE Implement decipherCaesarShift (String ciphertext)
		
		for (int i = 1; i <= 26; i++) {
			final int key = i;
			String shiftedText = shiftLetters(key, ciphertext);
		    if (isSentence(shiftedText.split(" "))) {
		    	return shiftedText;
		    }
		}
		return "This ciphertext has non-English plaintext";
	}
	
	/** Generates a random integer key between 1 and 26 (inclusive) and shifts all letters in the given plain text by this key
	 * @return the Caesar-shift-cipher-encrypted cipher text
	 */
	public static String encryptCaesarShift(String plaintext) {
		String ciphertext = shiftLetters((int)(Math.random() * 25), plaintext);
		return ciphertext;
	}
	
	/** 
	 * Get the number of occurences of a given letter in a given text
	 * @param text the excerpt from which the number of occurences of the letter is counted
	 * @param letter the character that is being counted
	 * @return the number of occurences of the letter as a long
	 */
	private static long getOccurences(String text, char letter) {
		long frequencyOfLetter = convertStringToListOfCharacters(text).stream().filter(e -> {
			return e.equals(letter);
		}).count();
		return frequencyOfLetter;
	}
	
	/**
	 * @param text
	 * @return
	 */
	private static Object[] getListOfOccurences(String text) {
		Stream<Long> alphabetCollection = ALPHABET.stream().map(l -> {
			return getOccurences(text, l);
		});
		
		List<Long> listOfOccurences = alphabetCollection.collect(Collectors.toList());
		ArrayList<ArrayList<Object>> letterOccurencesPairs = new ArrayList<ArrayList<Object>>();
		for (int i = 0; i < ALPHABET.size(); i++) {
			ArrayList<Object> letterOccurencesPair = new ArrayList<Object>();
			// First element is letter as Character
			letterOccurencesPair.add(ALPHABET.get(i)); 
			// Second element is number of occurences as Long
			letterOccurencesPair.add(listOfOccurences.get(i)); 
			// Add the array list to the outer array list
			letterOccurencesPairs.add(letterOccurencesPair);
		}
		
		System.out.println(letterOccurencesPairs.toString());
		
		return letterOccurencesPairs.toArray();
		
	}
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	private static Object[][] getSortedListOfOccurences(String text) {
		
		Object[][] listOfOccurences = getListOfOccurences(text);
		
		// Sort the 2 dimensional list into smallest to largest by number of occurences
		Arrays.sort(listOfOccurences, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				// Get two occurences as integers
		        Long quantityOne = (Long) o1[1];
			    Long quantityTwo = (Long) o2[1];
			    // Compare each number of occurences to each other
			    return quantityOne.compareTo(quantityTwo);
			}
		} );
		System.out.println("sorted list of occurences: " + listOfOccurences.toString());
		
		return listOfOccurences;
	}
	
	/**	
	 * 
	 * @param text
	 * @return the differences between each occurence
	 */
	private static List<Integer> getDifferencesOfOccurences(String text) {
		Object[][] sortedListOfOccurences = getSortedListOfOccurences(text);
		
		// Initialize an empty array list
		ArrayList<Integer> listOfDifferences = new ArrayList<Integer>(); 
		
		for (int i = 1; i < sortedListOfOccurences.length; i++) {
			System.out.println(sortedListOfOccurences[i]);
			// Add the absolute difference as an int to the listOfDifferences list
			listOfDifferences.add(
				Math.toIntExact(
					Math.abs(
					    (Long)sortedListOfOccurences[i][1] - (Long)sortedListOfOccurences[i - 1][1]
					)
				)
			); 
		}
		
		System.out.println("List of differences: " + listOfDifferences);
		
		return listOfDifferences;
	}
	
	/** 
	 * @param ciphertext the cipher that is deciphered
	 * @return the completely deciphered or almost completely deciphered monoalphabetic substitution cipher in plaintext
	 */
	public static String decipherMonoalphabetic(String ciphertext) {
		// TODO Implement decipherMonoalphabetic(String ciphertext) method
		
		List<Integer> listOfDifferences = getDifferencesOfOccurences(ciphertext);
		int highestDifference = Collections.max(listOfDifferences);
		
		return "" + highestDifference + ""; 
	}
	
	/** Creates a cipher alphabet
	 * @return the cipher alphabet - the shuffled plaintext alphabet
	 */
	private static List<Character> generateCipherAlphabet() {
		List<Character> cipheralphabet = new ArrayList<Character>();
		for (int i = 0; i < 26; i++) {
		    cipheralphabet.add(ALPHABET.get(i));
		}
		
		Collections.shuffle(cipheralphabet);
		
		return cipheralphabet;
	}

	/** 
	 * Encrypts a message into a monoalphabetic cipher
	 * @param plaintext
	 * @return
	 */
	public static String encryptMonoalphabetic(String plaintext) {
		List<Character> plaintextchars = convertStringToListOfCharacters(plaintext);
		List<Character> cipherAlphabet = generateCipherAlphabet();
		List<Character> plainAlphabet = ALPHABET;
		
		List<Character> cipherchars = plaintextchars.stream().map(p -> {
			if (isSpaceOrPunctuation(p)) {
				return p;
			}
			
			int nthLetter = plainAlphabet.indexOf(p);
			
			return cipherAlphabet.get(nthLetter);
		}).collect(Collectors.toList());	
		
		return convertListToString(cipherchars);
	}
	
	/** 
	 * @param ciphertext
	 * @return the completely deciphered or almost completely deciphered Vigenere cipher in plaintext
	 */
	public static String decipherVigenere(String ciphertext) {
		// TODO Implement decipherVigenere(String ciphertext) method
		Pattern p = Pattern.compile(".*(.+).*\1.*");
		Matcher m = p.matcher(ciphertext);
		Boolean b = m.matches();
		System.out.println(b);
		String plaintext = decipherMonoalphabetic(ciphertext);
		return plaintext; 
	}

	/**
	 * @param list the list of characters
	 * @return the converted string
	 */
	private static String convertListToString(List<Character> list)
	{    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Object ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}
	
	/**
	 * Used for ease when copying lists from the web
	 * @param value the set of strings divided by spaces as one string
	 * @return the set of strings as an array list of strings
	 */
	private static List<String> convertStringToListOfStrings(String value) {
		return new ArrayList<String>(Arrays.asList(value.split(" ")));
	}
	
	/** Converts a string to a list of characters
	 * @param value of the string that needs to be converted
	 * @return the converted list
	 */
	private static List<Character> convertStringToListOfCharacters(String value) {
		List<Character> cipherchars = new ArrayList<Character>();
		for (char ch: value.toCharArray()) {
			cipherchars.add(Character.toLowerCase(ch));
		}
		
		return cipherchars;
	}
}
