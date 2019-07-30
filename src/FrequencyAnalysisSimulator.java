/**
 * Frequency Analysis Simulator
 */

/**
 * @author Varun Singh
 * @inspiration The Code Book by Simon Singh
 */

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
			decipherCaesarShift(ciphertext);
		}
		else if (ciphertype.equals("monoalphabetic")) {
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
	
	public static void decipherCaesarShift(String ciphertext) {
		String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
		String[] cipherletters = ciphertext.split("");
		
		List<Character> numbers = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	       List<Integer> squares = numbers.stream()
	                                      .map( c -> numbers.indexOf(c))
	                                      .collect(Collectors.toList());
	       System.out.println(squares);
//		for (int key = 1; key <= 26; key++) {
//			System.out.println("Key: " + key);
//			Arrays.asList(cipherletters).stream().map(n -> {
//				int newPosition = Arrays.asList(alphabet).indexOf(n) + key;
//				System.out.println("The new position of the letter " + n + " is " + newPosition);
//				return newPosition;
//			}).collect(Collectors.toSet());
//		}
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
