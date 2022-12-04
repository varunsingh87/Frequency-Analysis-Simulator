package frequencyanalysissimulator.business;

public final class FrequencyAnalysis {
	static double[] standardEnglishFrequencies = new double[] {
			0.082, 0.014, 0.028, 0.038, 0.131, 0.029, 0.020, 0.053, 0.064, 0.001, 0.004, 0.034, 0.025, 0.071, 0.080,
			0.020, 0.001, 0.068, 0.061, 0.105, 0.025, 0.009, 0.015, 0.002, 0.020, 0.001
	};

	static int[] calculateAbsoluteLetterFrequencies(String text) {
		int[] ciphertextletterCounts = new int[26];

		// Populate ciphertext letter frequencies by adding one for every occurrence of
		// letter O(n)
		for (int i = 0; i < text.length(); i++) {
			char letter = text.charAt(i);
			int asNum = letter - 65;
			// Ignore anything other than letters (such as spaces)
			if (Character.isAlphabetic((int) letter)) {
				ciphertextletterCounts[asNum]++;
			}
		}

		return ciphertextletterCounts;
	}
}
