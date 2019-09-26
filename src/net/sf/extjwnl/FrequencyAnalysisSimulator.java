/*
OF MIT 34 BTAKL LOFET MIT K.D.L. MOMAFOE CAL ROLEGXTKTR GF MIT LTAYSGGK LGWMI GY FTCYGWFRSAFR, OM IAL ZTEGDT MIT CGKSR'L DGLM YADGWL LIOHCKTEQ -- A KWLMOFU IWSQ ALLAOSTR ZB IWFRKTRL GY TVHSGKTKL AFR DGXOTDAQTKL, LASXGKL AFR MGWKOLML, LEOTFMOLML AFR YTRTKAS CAMEIRGUL. ASS AUKTT MIAM MIT GFET-UKAFR LIOH OL KAHORSB YASSOFU AHAKM. KTLMOFU GF MIT OEB FGKMI AMSAFMOE LTAZTR DGKT MIAF MCG DOSTL RGCF, WHKOUIM ZWM LHSOM OF MCG, MIT YKAUOST DALL OL LSGCSB LWEEWDZOFU MG KWLM, EGKKGLOXT LASML, DOEKGZTL AFR EGSGFOTL GY RTTH-LTA EKTAMWKTL.
*/

package net.sf.extjwnl; // Uses the extjwnl package to determine if strings are sentences

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

import alphastats.*; // Uses my custom package for statistics and generalizations

import helperfoo.Converters;

import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

/**
 * Frequency Analysis Simulator
 * By Varun Singh
 */

/**
 * <h1>Frequency Analysis Simulator</h1>
 * <p>Frequency Analysis Simulator is a Java program that simulates frequency analysis in which the user inputs cipher text into the console and the System outputs as close to the corresponding plain text as possible. If the type of cipher has been identified, the process may be sped up after the user inputs the type of cipher (monoalphabetic or Vigenere) on prompt. Furthermore, Frequency Analysis Simulator can decipher the caesar shift cipher, a cipher that does not involve the use of frequency analysis for decipherment. As another added bonus, the application is able to encrypt messages.</p>
 * @author Varun Singh
 * @inspiration The Code Book by Simon Singh
 * @citations
 	* Princeton University "About WordNet." WordNet. Princeton University. 2010.
 	* <br>
 	* Stack Overflow, Inc.
 	* <br>
 	* Gale......
 	* <br>
 	* The Code Book
 */
public class FrequencyAnalysisSimulator {
	
	static Scanner userInput = new Scanner (System.in);
	static enum ACTION {
		ENCRYPT,
		DECRYPT,
		MAGIC
	}
	public static List<Character> ALPHABET = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	static char[] CHARS_TO_SKIP = { ' ', '!', '.', '?', ',', ';', '\'', '"', '(', ')', '[', ']', '{', '}'};
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
		else if (action.equals(ACTION.MAGIC))
			System.out.print(decipherMonoalphabetic("OF MIT 34 BTAKL LOFET MIT K.D.L. MOMAFOE CAL ROLEGXTKTR GF MIT LTAYSGGK LGWMI GY FTCYGWFRSAFR, OM IAL ZTEGDT MIT CGKSR'L DGLM YADGWL LIOHCKTEQ -- A KWLMOFU IWSQ ALLAOSTR ZB IWFRKTRL GY TVHSGKTKL AFR DGXOTDAQTKL, LASXGKL AFR MGWKOLML, LEOTFMOLML AFR YTRTKAS CAMEIRGUL. ASS AUKTT MIAM MIT GFET-UKAFR LIOH OL KAHORSB YASSOFU AHAKM. KTLMOFU GF MIT OEB FGKMI AMSAFMOE LTAZTR DGKT MIAF MCG DOSTL RGCF, WHKOUIM ZWM LHSOM OF MCG, MIT YKAUOST DALL OL LSGCSB LWEEWDZOFU MG KWLM, EGKKGLOXT LASML, DOEKGZTL AFR EGSGFOTL GY RTTH-LTA EKTAMWKTL."));
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
		for (char item : CHARS_TO_SKIP) {
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
		
		List<Character> unshiftedChars = Converters.convertStringToListOfCharacters(unshiftedText);
		
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
	    
	      return Converters.convertListToString(shiftedText);
	}

	/** Determines if a string is in the extended Java WordNet Library dictionary and has the correct part of speech
	 * @param pos
	 * @param word
	 * @return whether the given string is an English word
	 * @throws JWNLException
	 */
	protected static boolean isWord(String word) throws JWNLException {
		// DONE Implement isWord(String word) method
		Dictionary d = Dictionary.getDefaultResourceInstance();
		
		Collection<POS> POSList = EnumSet.allOf(POS.class);
		
		List<String> reflexivepronouns = Converters.convertStringToListOfStrings("myself yourself herself himself itself ourselves yourselves themselves");
		List<String> outliers = Converters.convertStringToListOfStrings("the this that of these those and you for");
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
		long frequencyOfLetter = Converters.convertStringToListOfCharacters(text).stream().filter(e -> {
			return e.equals(letter);
		}).count();
		return frequencyOfLetter;
	}
	
	private static long getOccurences(String text, String letters) {
		long frequencyOfLetter = Converters.convertStringToListOfStrings(text).stream().filter(e -> {
			return e.contains(letters);
		}).count();
		return frequencyOfLetter;
	}
	
	/**
	 * @param text
	 * @return
	 */
	private static Object[][] getListOfOccurences(String text) {
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
			// Third element is to be set as difference between the index 1 and index 1 of the previous array

			// Add the array list to the outer array list
			letterOccurencesPairs.add(letterOccurencesPair);
		}
		
		Object[][] letterOccurencesPairs1 = toMultiDimensionalArray(letterOccurencesPairs);
		
		return letterOccurencesPairs1;
		
	}
	
	private static Object[][] toMultiDimensionalArray(ArrayList<ArrayList<Object>> ec) {
		Object[][] ei = new Object[26][3];
		for (int i = 0; i < ec.size(); i++) {
		    List<Object> row = ec.get(i); // Get each row

		    // Perform equivalent `toArray` operation
		    Object[] copy = new Object[3];
		    for (int j = 0; j < row.size(); j++) {
		        // Manually loop and set individually
		        copy[j] = row.get(j);
		    }

		    ei[i] = copy;
		}
		return ei;
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
		
		return listOfOccurences;
	}
	
	/**	
	 * 
	 * @param text
	 * @return the differences between each occurence
	 */
	private static Object[][] getDifferencesOfOccurences(String text) {
		Object[][] sortedListOfData = getSortedListOfOccurences(text);
		
		for (int i = 1; i < sortedListOfData.length; i++) {
			// Add the absolute difference as an int to the sortedListOfData array
			sortedListOfData[i][2] = Math.toIntExact(
				Math.abs(
					(Long)sortedListOfData[i][1] - (Long)sortedListOfData[i - 1][1]
				)
			); 
		}
		
		return sortedListOfData;
	}
	
	private static ArrayList<Integer> getColAsInts(Object[][] matrix, int colIndex) {
		ArrayList<Integer> intCol = new ArrayList<Integer>();
		for (int i = 1; i < matrix.length; i++) {
			Object[] row = matrix[i];
			intCol.add((Integer)row[colIndex]);
		}
		
		return intCol;
	}
	
	private static ArrayList<Character> getColAsChars(Object[][] matrix, int colIndex) {
		ArrayList<Character> charCol = new ArrayList<Character>();
		for (Object[] row : matrix) {
			charCol.add((Character)row[colIndex]);
		}
		
		return charCol;
	}
	
	/**
	 * 
	 * @param matrix
	 * @param colIndex
	 * @return
	 */
	private static int maxCol(Object [][] matrix, int colIndex) {
	    Integer max = Collections.max(getColAsInts(matrix, colIndex));

	    return max;
	}
	
	/**
	 * 
	 * @param ciphertext
	 * @return
	 */
	private static int getHighestDifference(String ciphertext, Object[][] listOfDifferences) {
		int highestDifference = maxCol(listOfDifferences, 2);
		
		return highestDifference;
	}
	
	/**
	 * Gets the number of occurences of the least frequent of the most frequent letters
	 * @param ciphertext
	 * @return
	 */
	protected static int getLeastFrequentMostFrequentLetterFrequency(String ciphertext, Object[][] listOfDifferences) {
		int highestDifference = getHighestDifference(ciphertext, listOfDifferences);
		for (int i = 1; i < ALPHABET.size(); i++) {
			if ((int)listOfDifferences[i][2] == highestDifference) {
				
				return Math.toIntExact((long)listOfDifferences[i][1]);
			}
		}
		return 0;
	}
	
	/**
	 * 
	 * @param ciphertext
	 * @return
	 */
	protected static Object[][] getMostFrequentLetters(String ciphertext, Object[][] listOfDifferences) {	
		
		int leastFrequentMostFrequentLetterFrequency = getLeastFrequentMostFrequentLetterFrequency(ciphertext, listOfDifferences);
		Object[][] mostFrequentLetters = Arrays.asList(listOfDifferences).stream().filter(a -> {

			return (long)a[1] >= leastFrequentMostFrequentLetterFrequency;
		}).toArray(Object[][]::new);
		
		return mostFrequentLetters;
	}

	
	/**
	 * Deciphers certain letters based on their bigrams and double letters
	 * @param ciphertext
	 * @param listOfDifferences
	 * @return text with the letters that have been deciphered replaced with lowercase plaintext letters
	 */
	private static String findBasedOnBigrams(String ciphertext) throws JWNLException {
		String ciphertext1 = ""; 
		List<String> goodWords = Arrays.asList(
			ciphertext.split(" "))
				.stream()
				.filter(
					word -> {
						System.out.println(word);
						return AlphabeticalStatistics.meetsAllConditions(
								AlphabeticalStatistics.isNWords(
										word, 3), 
								AlphabeticalStatistics.hasDoubleInWord(
										word)
						);
					}
				)
				.collect(
					Collectors.toList()
				);
		System.out.println(Converters.convertListToString(goodWords));
		for (String word : goodWords) {
			for (Character c : AlphabeticalStatistics.DOUBLE_LETTERS) {
				char doub = AlphabeticalStatistics.doubleLetterInWord(word);
				System.out.println(doub + ", " + c);
				String word1 = word.replace(doub, c);
				System.out.println(word1);
				
				if (isWord(word1) ) {
					ciphertext1 = ciphertext.replace(word, word1.toLowerCase());
				}
			}
		}
		
		return ciphertext1;
	}
	
	/** 
	 * @param ciphertext the cipher that is deciphered
	 * @return the completely deciphered or almost completely deciphered monoalphabetic substitution cipher in plaintext
	 */
	public static String decipherMonoalphabetic(String ciphertext) throws JWNLException {
		// TODO Implement decipherMonoalphabetic(String ciphertext) method
		Object[][] listOfDifferences = getDifferencesOfOccurences(ciphertext);
		Object[][] mostFrequentLetters = getMostFrequentLetters(ciphertext, listOfDifferences);
		String bigramData = findBasedOnBigrams(ciphertext);
		return bigramData;
	}
	
	/** Creates a cipher alphabet
	 * @return the cipher alphabet - the shuffled plaintext alphabet
	 */
	private static List<Character> generateCipherAlphabet() {
		List<Character> cipheralphabet = new ArrayList<Character>();
		for (char letter : ALPHABET) {
		    cipheralphabet.add(letter);
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
		List<Character> plaintextchars = Converters.convertStringToListOfCharacters(plaintext);
		List<Character> cipherAlphabet = generateCipherAlphabet();
		List<Character> plainAlphabet = ALPHABET;
		
		List<Character> cipherchars = plaintextchars.stream().map(p -> {
			if (isSpaceOrPunctuation(p)) {
				return p;
			}
			
			int nthLetter = plainAlphabet.indexOf(p);
			
			return cipherAlphabet.get(nthLetter);
		}).collect(Collectors.toList());	
		
		return Converters.convertListToString(cipherchars);
	}
	
	/** 
	 * @param ciphertext
	 * @return the completely deciphered or almost completely deciphered Vigenere cipher in plaintext
	 */
	public static String decipherVigenere(String ciphertext) throws JWNLException {
		// TODO Implement decipherVigenere(String ciphertext) method
		Pattern p = Pattern.compile(".*(.+).*\1.*");
		Matcher m = p.matcher(ciphertext);
		Boolean b = m.matches();
		System.out.println(b);
		String plaintext = decipherMonoalphabetic(ciphertext);
		return plaintext; 
	}
}
