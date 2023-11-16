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
	/**
	 * @param args arg[0]: Input must be one line
	 *             arg[1]: Trial Id for output file
	 *             arg[2]: (Optional) key length calculation algorithm
	 *             arg[3]: (Optional) Caesar decryption algorithm
	 *             arg[4]: (Optional): key
	 */
	public static void main(String[] args) {
		final String key = args.length > 4 ? args[4].toUpperCase() : "DONQUIXOTECOYOTEWILL";
		// Remove all non-letters
		String expectedText = args[0].replaceAll("[^A-Za-z]", "").toUpperCase();
		StringBuilder output = new StringBuilder("Len,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,Avg(" + args[1] + ")\n");

		for (int cipherlen = 90; cipherlen <= 1000; cipherlen += 50) { // O(1)
			output.append(cipherlen).append(",");
			double avg = 0;
			for (int i = 3; i <= 20; i++) {
				String input = expectedText.substring(0, cipherlen);
				String subKey = key.substring(0, i);
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
					"data/outputs/%s/%s_%s", key, args[2].toLowerCase(), args[3].toLowerCase()
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
