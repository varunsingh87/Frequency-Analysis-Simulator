package secretwriting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
		
		System.out.println(getText());
		
		Pair mostFrequentFinalLetter = f.getMostFrequentFinalLetter();
		Pair vowel = f.getMostSocialLetter();
		Pair threeLetterWord = f.getMostFrequentTrigraph();
		Pair mostFrequentInitialLetter = f.getMostFrequentInitialLetter();
		
		setText(f.replaceBigrams());
		setText(getText().replace(mostFrequentFinalLetter.props, "s"));
		setText(getText().replace(vowel.props, "a"));
		setText(getText().replace(threeLetterWord.props, "the"));
		setText(getText().replace(mostFrequentInitialLetter.props, "t"));
		
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
