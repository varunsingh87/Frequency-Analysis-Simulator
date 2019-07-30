/**
 * Frequency Analysis Simulator
 */

/**
 * @author Varun Singh
 * @inspiration The Code Book by Simon Singh
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FrequencyAnalysisSimulator {
	
	static Scanner userInput = new Scanner (System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ciphertext = determineCiphertext();
		String ciphertype = determineCipherType();
		if (ciphertype.equalsIgnoreCase("Caesar shift")) {
			for (int key = 1; key <= 26; key++) {
				System.out.println(decipherCaesarShift(ciphertext, key));
			}
		} else if (ciphertype.equals("monoalphabetic")) {
			System.out.println(decipherMonoalphabetic(ciphertext));
		} else if (ciphertype.equals("Vigenere")) {
			System.out.println(decipherVigenere(ciphertext));
		}
	}
	
	/**
	 * @return the type of substitution cipher
	 */
	public static String determineCipherType() {
		// DONE Implement determineCipherType()
		System.out.println("If you know the type of cipher this ciphertext is, enter it now. Otherwise, enter a new line.");
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
	
	public static String decipherCaesarShift(String ciphertext, int key) {
		List<Character> cipherchars = new ArrayList<Character>();
		for (char ch: ciphertext.toCharArray()) {
			cipherchars.add(Character.toLowerCase(ch));
		}
		List<Character> alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	    List<Character> decipheredText = cipherchars.stream()
	                                      .map( c -> {
	                                    	  int newPosition = alphabet.indexOf(c) + key;
	                                    	  if (newPosition == 26) {
	                                    		  newPosition = 0;
	                                    	  }
	                                    	  return alphabet.get(newPosition);
	                                      })
	                                      .collect(Collectors.toList());
	    String plaintext = getStringRepresentation(decipheredText);
	    return plaintext;
	}
	
	private static String getStringRepresentation(List<Character> list)
	{    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}
	
	/** 
	 * 
	 * @param ciphertext the cipher that is deciphered
	 * @return the completely deciphered or almost completely deciphered monoalphabetic substitution cipher in plaintext
	 */
	public static String decipherMonoalphabetic(String ciphertext) {
		// TODO Implement decipherMonoalphabetic(String ciphertext) method
		String plaintext = ciphertext;
		return plaintext; 
	}
	
	/** 
	 * 
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

}
