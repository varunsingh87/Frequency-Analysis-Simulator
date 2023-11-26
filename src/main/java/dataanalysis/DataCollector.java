package dataanalysis;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import frequencyanalysissimulator.crypto.CaesarDecryptionMethod;
import frequencyanalysissimulator.crypto.KeyLengthMethod;
import frequencyanalysissimulator.crypto.Vigenere;
import frequencyanalysissimulator.crypto.VigenereDecryption;

public class DataCollector {
	private final static int PREFERRED_CIPHER_LENGTH = 1000;
	private final static int PREFERRED_KEY_LENGTH = 20;
	private final static int MIN_SUBKEY_LENGTH = 3;
	private final static String DEFAULT_KEY = "DONQUIXOTECOYOTEWILL";

	/**
	 * @param args arg[0]: Input must be one line
	 *             arg[1]: Trial Id for output file
	 *             arg[2]: (Optional) key length calculation algorithm
	 *             arg[3]: (Optional) Caesar decryption algorithm
	 *             arg[4]: (Optional): key
	 */
	public static void main(String[] args) {
		final String fullInput = args[0];
		final String fullKey = args.length > 4 ? args[4].toUpperCase() : DEFAULT_KEY;
		// Remove all non-letters
		String expectedText = args[0].replaceAll("[^A-Za-z]", "").toUpperCase();
		StringBuilder output = new StringBuilder("Len,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,Avg(" + args[1] + ")\n");

		for (int cipherlen = 90; cipherlen <= PREFERRED_CIPHER_LENGTH; cipherlen += 50) { // O(1)
			double avg = 0;

			output.append(cipherlen).append(",");
			if (cipherlen > fullInput.length()) {
				output.append(fillEmptyRows(cipherlen));
				break;
			}

			for (int keylen = 3; keylen <= PREFERRED_KEY_LENGTH; keylen++) {
				if (keylen > fullKey.length()) {
					output.append(fillEmptyCells(keylen));
					break;
				}

				String input = expectedText.substring(0, cipherlen);
				String subKey = fullKey.substring(0, keylen);
				String ciphertext = Vigenere.encrypt(subKey, input);
				VigenereDecryption v = args[2] != null ? new VigenereDecryption(ciphertext, KeyLengthMethod.valueOf(args[2].toUpperCase())) : new VigenereDecryption(ciphertext);
				String decryptedText = v.decrypt(CaesarDecryptionMethod.valueOf(args[3]));
				double accuracy = percentageSimilarity(decryptedText, input);

				output.append(Math.round(accuracy)).append(",");
				avg += accuracy;
			}

			output.append(Math.round(avg / 18.0)).append("\n");
		}

		try {
			String dataOutputFileName = String.format(
					"data/outputs/%s/%s_%s", fullKey, args[2].toLowerCase(), args[3].toLowerCase()
			);
			Files.createDirectories(Paths.get(dataOutputFileName));
			FileWriter writer = new FileWriter(dataOutputFileName + "/" + args[1] + ".csv");
			writer.append(output);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fills the rest of the data table with 0 values to represent that the cipher is not that long
	 */
	private static String fillEmptyRows(int startingCipherLength) {
		return fillEmptyCells(MIN_SUBKEY_LENGTH).concat("\n")
				.repeat(PREFERRED_CIPHER_LENGTH - startingCipherLength);
	}

	/**
	 * Fills a row with 0 values to represent that the key is not that long
	 */
	private static String fillEmptyCells(int startingKeyLenght) {
		return "0,".repeat(PREFERRED_KEY_LENGTH - startingKeyLenght);
	}

	/**
	 * Preconditions:
	 * (1) input1 is the same exact size as input2 (input1.length() == input2.length())
	 * (2) input1 and input2 have all the non-letters in the same exact places
	 *
	 * @return Ratio of exact matches to input size as a percentage
	 * @throws IndexOutOfBoundsException If input1.length() !== input2.length()
	 */
	static float percentageSimilarity(String input1, String input2) {
		float count = 0;
		for (int i = 0; i < input1.length(); i++) {
			if (input1.charAt(i) == input2.charAt(i)) {
				count += 1.0 / input1.length();
			}
		}

		return count * 100;
	}
}
