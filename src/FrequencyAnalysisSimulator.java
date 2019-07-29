/**
 * Frequency Analysis Simulator
 */

/**
 * @author Varun Singh
 * @inspiration The Code Book by Simon Singh
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrequencyAnalysisSimulator {
	
	static Scanner userInput = new Scanner (System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ciphertext = determineCiphertext();
		if (determineCipherType().equals("monoalphabetic")) {
			System.out.println(decipherMonoalphabetic(ciphertext));
		} else if (determineCipherType().equals("Vigenere")) {
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
		if (cipherType.equals("monoalphabetic")) {
			return "monoalphabetic";
		} else {
			return "Vigenere";	
		}
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
