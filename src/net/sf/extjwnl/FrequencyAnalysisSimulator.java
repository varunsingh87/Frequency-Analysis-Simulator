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

package net.sf.extjwnl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

public class FrequencyAnalysisSimulator {
	
	static Scanner userInput = new Scanner (System.in);
	static enum ACTION {
		ENCRYPT,
		DECRYPT
	}
	static List<Character> alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
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
		
		System.out.println("Would you like to \na) encrypt \nb) decrypt?");
		String myString = userInput.nextLine();
		
		return ACTION.valueOf(myString.toUpperCase());
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
		System.out.println("Enter a plaintext: ");
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
		}
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
		IndexWord method = d.lookupIndexWord(POS.NOUN,  word);
		// Check if this is a word
		return method != null;
	}
	
	/**
	 * 
	 * @param words
	 * @return
	 * @throws JWNLException
	 */
	private static boolean isSentence(String[] words) throws JWNLException {
		for (String word : words) {
			if (isWord(word))
				break;
			else
				return false;
		}
		
		return true;
	}
	
	/**
	 * @param key the number of letters to shift to the right from the original letter
	 * @param unshiftedText the ciphertext or the plaintext
	 * @return the shifted output message
	 */
	private static String shiftLetters(final int key, String unshiftedText) {
		// DONE Implement shiftLetters(final int key, String unshiftedText)
		
		List<Object> unshiftedChars = convertStringToList(unshiftedText);
		
	    List<Object> shiftedText = unshiftedChars.stream()
	      .map( c -> {
	    	  
	    	  // Skip spaces and punctuation
	    	  char[] items = { ' ', '!', '.', '?', ',', ';' };
	    	  for (char item : items) {
	    	      if (c.equals(item)) {
	    	          return c; // No need to look further.
	    	      } 
	    	  }
	    	  
        	  
        	  // Shift the letter by an integer, key
        	  int index = alphabet.indexOf(c);
        	  int newPosition = index + key;
        	  if (newPosition > 25) {
        		  newPosition -= 26; 
        	  }
        	  
        	  return alphabet.get(newPosition); // Return the new position
	      }).collect(Collectors.toList());
	    
	      return convertListToString((List<Object>)shiftedText);
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
		String ciphertext = shiftLetters((int)Math.round(Math.random() * 26), plaintext);
		return ciphertext;
	}
	

	
	/** 
	 * @param ciphertext the cipher that is deciphered
	 * @return the completely deciphered or almost completely deciphered monoalphabetic substitution cipher in plaintext
	 */
	public static String decipherMonoalphabetic(String ciphertext) {
		// TODO Implement decipherMonoalphabetic(String ciphertext) method
		String plaintext = ciphertext;
		return plaintext; 
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
	private static String convertListToString(List<Object> list)
	{    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Object ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}
	
	/** Converts a string to a list of characters
	 * @param value of the string that needs to be converted
	 * @return the converted list
	 */
	private static List<Object> convertStringToList(String value) {
		List<Object> cipherchars = new ArrayList<Object>();
		for (char ch: value.toCharArray()) {
			cipherchars.add(Character.toLowerCase(ch));
		}
		
		return cipherchars;
	}
	
	/**
	 * @return
	 */
	private static List<Character> generateCipherAlphabet() {
		List<Character> cipheralphabet = new ArrayList<Character>();
		for (int i = 1; i <= 25; i++) {
		    cipheralphabet.add(alphabet.get(i));
		}
		
		Collections.shuffle(cipheralphabet);
		
		return cipheralphabet;
	}
}
