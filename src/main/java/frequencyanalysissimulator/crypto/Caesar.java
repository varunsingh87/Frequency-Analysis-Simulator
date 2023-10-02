package frequencyanalysissimulator.crypto;

/**
 * Represents a Caesar Shift cipher that will be decrypted
 */
public class Caesar {
	private String ciphertext;
	private int[] ciphertextAsNumbers;

	/**
	 * Instantiates a Caesar object with a given ciphertext
	 *
	 * @param t The ciphertext.
	 *          Precondition: {@code t} must have been encrypted using a Caesar cipher
	 */
	public Caesar(String t) {
		ciphertext = String.join("", t.split("[ \r\t\n]")).toUpperCase(); // Remove whitespaces and carriage returns
		ciphertextAsNumbers = new int[t.length()];
	}

	/**
	 * Decrypts a Caesar cipher using the passed method of frequency analysis
	 *
	 * @param m The CaesarDecryptionMethod (enum)
	 * @return The decrypted text
	 */
	public String decrypt(CaesarDecryptionMethod m) {
		switch (m) {
			case KASISKI:
				return decryptByKasiski();
			case KERCKHOFF:
			default:
				char keyLet = getKeyByChiSquare();
				int keyLetAsNum = 27 - (keyLet - 64);
				return Caesar.encrypt(ciphertext, keyLetAsNum);
		}
	}

	/**
	 * Use most common occurence, assume it's e, and shift the rest using this (Kasiski's original
	 * method of finding the key)
	 *
	 * @return Kasiski-decrypted plaintext
	 */
	public String decryptByKasiski() {
		int[] frequencies = FrequencyAnalysis.calculateAbsoluteLetterFrequencies(ciphertext);
		int maxFreq = 0;
		int mostCommonLetterAsNumber = 0;
		for (int i = 0; i < frequencies.length; i++) {
			if (frequencies[i] > maxFreq)
				maxFreq = frequencies[i];
			mostCommonLetterAsNumber = i;
		}

		int key = mostCommonLetterAsNumber - 4;
		return decrypt(key);
	}

	/**
	 * Decrypts an individual Caesar cipher using Chi Square analysis
	 * to find which number of shifts matches the standard English frequency
	 * distribution the most
	 * AKA Kerckhoff's Method
	 *
	 * @return The letter that represents the number of rotations for the Caesar
	 * cipher and part of the key for the Vigenere cipher
	 * @time O(n) - Increases linearly with the length of the ciphertext
	 */
	public char getKeyByChiSquare() {
		double[] ciphertextLetterFrequencies = new double[26];
		// Populate ciphertext letter frequencies by adding one for every occurrence of
		// letter O(n)
		for (int i = 0; i < ciphertext.length(); i++) {
			char letter = ciphertext.charAt(i);
			int asNum = letter - 65;
			ciphertextAsNumbers[i] = asNum;
			// Ignore anything other than letters (such as spaces)
			if (Character.isAlphabetic(letter)) {
				ciphertextLetterFrequencies[asNum] += 1.0 / ciphertext.length();
			}
		}

		// O(26 * 26) = O(c)
		double[] chiSquareValues = new double[26];
		for (int shift = 0; shift < 26; shift++) {
			double chiSquareValue = 0;
			for (int i = 0; i < 26; i++) {
				double standardFrequency = FrequencyAnalysis.standardEnglishFrequencies[i];
				double shiftedCiphertextFrequency = ciphertextLetterFrequencies[(i + shift) % 26];
				chiSquareValue += Math.pow(shiftedCiphertextFrequency - standardFrequency, 2)
						/ standardFrequency;
			}
			chiSquareValues[shift] = chiSquareValue;
		}

		// O(26) = O(c)
		double smallestChiSquareValue = Double.MAX_VALUE;
		char letter = 'A';
		for (int i = 0; i < chiSquareValues.length; i++) {
			if (chiSquareValues[i] < smallestChiSquareValue) {
				smallestChiSquareValue = chiSquareValues[i];
				letter = (char) (i + (int) 'A'); // Convert number to letter
			}
		}

		return letter;
	}

	/**
	 * Decrypts with key by encrypting the complementary number within 26
	 *
	 * @param key The key that was used to get to the current ciphertext
	 * @return The plaintext
	 */
	String decrypt(int key) {
		return encrypt(ciphertext, (26 - key) % 26);
	}

	/**
	 * Encrypts a text with the key where A = key with Casar Shift
	 *
	 * @param key Precondition: A-Z, one letter long
	 *            Postcondition: Encrypted Caesar ciphertext
	 * @apiNote A = 0, so encrypt('Sample', 'A') = 'Sample'
	 * @see frequencyanalysissimulator.crypto.Caesar#encrypt(java.lang.String, int)
	 */
	public static String encrypt(String plaintext, String key) {
		return encrypt(plaintext, (int) (key.charAt(0) - 65));
	}

	/**
	 * Precondition: 0 <= key <= 26
	 *
	 * @param key The shift
	 * @return The encrypted ciphertext
	 * @apiNote Equals this.inputText when key is 0 or 26
	 */
	public static String encrypt(String plaintext, int key) {
		key = key % 26; // Constrain key to [0, 26] so that shift operations are accurate

		StringBuilder ciphertext = new StringBuilder();

		for (char letter : plaintext.toCharArray()) {
			/*
			 * Subtract 64 - letters have ASCII codes starting at 65
			 * Add shift
			 * Subtract 1 - Otherwise a shift of 26 would become 0 when modulo is performed
			 * Stay within 0-25 range - later letters in the alphabet would otherwise go
			 * above
			 * Add 1 - Make that range 1 - 26
			 */
			int shifted = (letter - 64 + key - 1) % 26 + 1;
			ciphertext.append((char) (shifted + 'A' - 1));
		}

		return ciphertext.toString();
	}
}