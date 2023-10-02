package frequencyanalysissimulator.crypto;

import frequencyanalysissimulator.dictionary.RandomWord;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

import static frequencyanalysissimulator.dictionary.LetterArithmetic.ALPHABET;
import static frequencyanalysissimulator.dictionary.LetterArithmetic.letterOperator;

/**
 * Static utility class for encrypting and decrypting texts with the Vigenere cipher or a variant with a provided key
 */
public class Vigenere {
	private static String convert(String key, String text, BiFunction<Integer, Integer, Integer> shiftOperation) {
		final int keySize = key.length();
		StringBuilder s = new StringBuilder();
		int nonLetterCount = 0;
		for (int i = 0; i < text.length(); i++) {
			if (Character.isLetter(text.charAt(i))) {
				int keyOffset = letterOperator.toNumber(key.charAt((i - nonLetterCount) % keySize));
				int textMapping = letterOperator.toNumber(text.charAt(i));
				int shiftOperationResult = shiftOperation.apply(keyOffset, textMapping);
				s.append(letterOperator.toLetter(shiftOperationResult));
			} else {
				s.append(text.charAt(i));
				nonLetterCount++;
			}
		}

		return s.toString();
	}

	/**
	 * Encrypts a text with the Beaufort Classic cipher using the given key
	 *
	 * @param key  A alphabetical key such that {@code key.length() < text.length()}
	 * @param text The plaintext that will be encrypted
	 */
	public static String encryptBeaufortClassic(String key, String text) {
		return convert(key, text, (k, l) -> k - l);
	}

	/**
	 * Decrypts a text with the Beaufort classic cipher and the given key.
	 *
	 * @param key  The key that should have been used to encrypt the text
	 * @param text The Beaufort classic ciphertext that will be decrypted
	 */
	public static String decryptBeaufortClassic(String key, String text) {
		return convert(key, text, (k, l) -> k - l);
	}

	/**
	 * Encrypts a message with the variant Beaufort cipher algorithm
	 */
	public static String encryptBeaufortVariant(String key, String text) {
		return convert(key, text, (k, l) -> l - k);
	}

	/**
	 * Decrypts a variant-Beaufort-encrypted ciphertext
	 *
	 * @param key  The key that will be used to convert the ciphertext back to a plaintext
	 * @param text The ciphertext that will be decrypted
	 */
	public static String decryptBeaufortVariant(String key, String text) {
		return convert(key, text, Integer::sum);
	}

	/**
	 * Encrypts a message using the standard Vigenere cipher algorithm
	 *
	 * @param key  The key that will be used to convert the plaintext into a ciphertext
	 * @param text The plaintext message
	 * @return The ciphertext
	 */
	public static String encrypt(String key, String text) {
		return convert(key, text, Integer::sum);
	}

	/**
	 * Decrypts a standard-Vigenere-encrypted message with a provided key.
	 *
	 * @param key  The key that will be used to convert the ciphertext back to a plaintext.
	 *             Precondition: The same key was used to encrypt this {@code text}
	 * @param text The ciphertext.
	 *             Precondition: This cipher is a Vigenere cipher
	 * @return The plaintext
	 */
	public static String decrypt(String key, String text) {
		return convert(key, text, (k, l) -> l - k);
	}

	/**
	 * Encrypts a message with the Gronsfeld cipher algorithm
	 *
	 * @param key  The set of numbers that will be used to convert the plaintext into a ciphertext
	 * @param text A plaintext
	 * @return The Gronsfeld cipher corresponding to the provided plaintext and key
	 * @throws IllegalArgumentException If the {@code key} is not all digits
	 */
	public static String encryptGronsfeld(String key, String text) {
		if (!Pattern.matches("[0-9]+", key))
			throw new IllegalArgumentException("Key must be all digits, 0-9");

		StringBuilder mappedKey = new StringBuilder(key.length());
		for (char number : key.toCharArray()) {
			mappedKey.append(letterOperator.toLetter(Integer.parseInt(String.valueOf(number))));
		}

		return encrypt(mappedKey.toString(), text);
	}

	/**
	 * Decrypts a Gronsfeld cipher
	 *
	 * @param key  The key that will be used to convert the ciphertext back to a plaintext.
	 *             Precondition: The same key was used to encrypt this {@code text}
	 * @param text A ciphertext.
	 *             Precondition: This cipher is a Vigenere cipher.
	 * @return The plaintext
	 */
	public static String decryptGronsfeld(String key, String text) {
		if (!Pattern.matches("[0-9]+", key))
			throw new IllegalArgumentException("Key must be all digits, 0-9");

		StringBuilder mappedKey = new StringBuilder(key.length());
		for (char number : key.toCharArray()) {
			mappedKey.append(letterOperator.toLetter(Integer.parseInt(String.valueOf(number))));
		}

		return decrypt(mappedKey.toString(), text);
	}

	/**
	 * @param plaintext    Message to encipher
	 * @param ascending    Evolution of shift: true is ascending, false is descending, both starting at initial shift
	 * @param initialShift Initial shift before it evolves
	 * @return Trithemius-enciphered ciphertext
	 * @apiNote Negative initial shifts are supported, by becoming positive shifts that many counts from the end of the alphabet
	 */
	public static String encryptTrithemius(String plaintext, boolean ascending, int initialShift) {
		initialShift = Math.floorMod(initialShift, 26);
		String alphabet = String.valueOf(ALPHABET);
		String shiftedAlphabet = alphabet.substring(initialShift) + alphabet.substring(0, initialShift);

		if (ascending) {
			return encrypt(shiftedAlphabet, plaintext);
		} else {
			shiftedAlphabet = shiftedAlphabet.charAt(0) + new StringBuilder(shiftedAlphabet.substring(1)).reverse().toString();
			return encrypt(shiftedAlphabet, plaintext);
		}
	}

	/**
	 * Decrypts a Trithemius-encrypted cipher with a provided key.
	 *
	 * @param ciphertext The ciphertext.
	 *                   Precondition: This cipher is a Trithemius cipher
	 * @param ascending  Whether the shift evolves upwards or downwards
	 * @return The plaintext
	 */
	public static String decryptTrithemius(String ciphertext, boolean ascending, int initialShift) {
		initialShift = Math.floorMod(initialShift, 26);
		return encryptTrithemius(ciphertext, !ascending, -initialShift);
	}

	/**
	 * Encrypts a message with the Vernam (one-time pad) cipher
	 */
	public static String encryptVernam(String plaintext) {
		String key = RandomWord.generate(plaintext.length());
		return convert(key, plaintext, Integer::sum);
	}

	/**
	 * Encrypts a message with the autokey cipher
	 */
	public static String encryptAutokey(String primer, String plaintext) {
		String key = primer.concat(plaintext.replaceAll(" ", ""));
		return encrypt(key, plaintext);
	}

	/**
	 * Decrypts an autokey cipher
	 *
	 * @param primer     The primer
	 * @param ciphertext The encrypted message
	 * @return The message in plain text
	 */
	public static String decryptAutokey(String primer, String ciphertext) {
		if (ciphertext.isEmpty())
			return "";

		int min = Math.min(primer.length(), ciphertext.length());
		String prev = decrypt(primer, ciphertext.substring(0, min));
		return prev + decryptAutokey(prev, ciphertext.substring(min));
	}

	/**
	 * Encrypts a message with a running key cipher using a random word generator
	 *
	 * @param plaintext The message to encrypt
	 * @apiNote THIS METHOD HAS A SIDE EFFECT:
	 * Makes an external API call to obtain a random word to form the running key
	 */
	public static String[] encryptRunningKey(String plaintext) {
		StringBuilder runningKey = new StringBuilder();

		while (runningKey.length() < plaintext.length()) {
			try {
				runningKey.append(RandomWord.generateEnglish());
			} catch (IOException | InterruptedException e) {
				System.out.println("Could not generate English word: " + e.getLocalizedMessage());
				runningKey.append(RandomWord.generate(plaintext.length()));
			}
		}

		String ciphertext = encrypt(runningKey.toString(), plaintext);

		return new String[]{ciphertext, runningKey.toString()};
	}
}