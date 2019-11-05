package secretwriting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import alphastats.AlphabeticalStatistics;
import alphastats.Frequencies;
import helperfoo.Converters;
import helperfoo.EnglishDeterminer;
import helperfoo.Pair;

public class MonoalphabeticCipher extends Cipher {

	public MonoalphabeticCipher(String givenText) {
		super(givenText);
	}

	/**
	 * Creates a cipher alphabet
	 * 
	 * @return the cipher alphabet - the shuffled plaintext alphabet
	 */
	public ArrayList<Character> generateCipherAlphabet() {
		ArrayList<Character> cipheralphabet = new ArrayList<Character>();
		for (char letter : EnglishDeterminer.ALPHABET) {
			cipheralphabet.add(letter);
		}

		Collections.shuffle(cipheralphabet);

		return cipheralphabet;
	}

	/**
	 * @param ciphertext the cipher that is deciphered
	 * @return the completely deciphered or almost completely deciphered
	 *         monoalphabetic substitution cipher in plaintext
	 */
	public String decrypt() {
		// Object[][] listOfDifferences = getDifferencesOfOccurences(getText());
		// Object[][] mostFrequentLetters = getMostFrequentLetters(getText(),
		// listOfDifferences);
		Frequencies f = new Frequencies(this); // Defines a new Frequencies object
		List<String> solvedLetters = new ArrayList<String>();
		
		System.out.println(getText());
		
		Pair mostFrequentFinalLetter = f.getMostFrequentFinalLetter();
		Pair vowel = f.getMostSocialLetter();
		Pair threeLetterWord = f.getMostFrequentTrigraph();
		Pair mostFrequentInitialLetter = f.getMostFrequentInitialLetter();
		
		//setText(f.replaceBigrams());
		
		setText(getText().replace(mostFrequentFinalLetter.props, "s"));
		solvedLetters.add("s");

		setText(getText().replace(vowel.props, "a"));
		solvedLetters.add("a");
		
		String notSolvedTrigram = "   ";
		for (String trigram : AlphabeticalStatistics.TRIGRAPHS) {
			if (!solvedLetters.contains(trigram)) {
				notSolvedTrigram = trigram;
				System.out.println(trigram);
				break;
			}
		}
		
		if (notSolvedTrigram != "   ") {
			setText(getText().replace(threeLetterWord.props, notSolvedTrigram));
			for (char letter : notSolvedTrigram.toCharArray()) {
				solvedLetters.add(Character.toString(letter));
			}
		}
		
		char notSolvedInitialLetter = ' ';
		for (char initialLetter : AlphabeticalStatistics.INITIAL_LETTERS) {
			if (solvedLetters.contains(Character.toString(initialLetter))) {
				notSolvedInitialLetter = initialLetter;
			}
		}
		
		if (notSolvedInitialLetter != ' ') {
			setText(getText().replace(mostFrequentInitialLetter.props, Character.toString(AlphabeticalStatistics.INITIAL_LETTERS[0])));
			solvedLetters.add(Character.toString(notSolvedInitialLetter));
		}
		
		System.out.println(solvedLetters.toString());
		
		return getText();
	}

	/**
	 * Encrypts a message into a monoalphabetic cipher
	 * 
	 * @param plaintext
	 * @return
	 */
	public String encrypt() {
		List<Character> plaintextchars = Converters.convertStringToListOfCharacters(getText());
		List<Character> cipherAlphabet = generateCipherAlphabet();
		List<Character> plainAlphabet = EnglishDeterminer.ALPHABET;

		List<Character> cipherchars = plaintextchars.stream().map(p -> {
			if (EnglishDeterminer.isSpaceOrPunctuation(p) || EnglishDeterminer.isInteger(p.toString())) {
				return p;
			}

			int nthLetter = plainAlphabet.indexOf(Character.toLowerCase(p));

			return cipherAlphabet.get(nthLetter);
		}).collect(Collectors.toList());

		return Converters.convertListToString(cipherchars);
	}

	public String magic() {
		return null;
	}

	public void printCipherAlphabetAsTable() {

	}

}
