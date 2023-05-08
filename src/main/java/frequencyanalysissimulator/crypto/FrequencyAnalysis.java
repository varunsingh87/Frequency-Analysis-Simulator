package frequencyanalysissimulator.crypto;

public final class FrequencyAnalysis {
	static double[] standardEnglishFrequencies = new double[] {
			0.082, 0.014, 0.028, 0.038, 0.131, 0.029, 0.020, 0.053, 0.064, 0.001, 0.004, 0.034, 0.025, 0.071, 0.080,
			0.020, 0.001, 0.068, 0.061, 0.105, 0.025, 0.009, 0.015, 0.002, 0.020, 0.001
	};

	private int[] counts;
	private String text;

	FrequencyAnalysis(String t) {
		text = t;
		counts = new int[t.length()];
	}

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

	/**
	 * Friedman's Test on a possible key length
	 * I=sum(letter frequency * (letter frequency - 1)) / (Length of text * (Length
	 * of text - 1))
	 * Calculates the probability of two randomly picked symbols in a text to be equal
	 *
	 * @return I, the coincidence index
	 */
	static float calculateIndexOfCoincidence(String text) {
		float indexOfCoincidence = 0;

		int[] ciphertextLetterCounts = FrequencyAnalysis.calculateAbsoluteLetterFrequencies(text);

		for (int i = 0; i < 26; i++) {
			double countTimesCountMinusOne = ciphertextLetterCounts[i] * (ciphertextLetterCounts[i] - 1);
			indexOfCoincidence += countTimesCountMinusOne;
		}

		double bigN = text.replaceAll("\s", "").length();
		indexOfCoincidence /= bigN * Math.max(1, bigN - 1);

		return indexOfCoincidence;
	}
}
