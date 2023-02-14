package secretwriting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import alphastats.AlphabeticalStatistics;
import alphastats.Frequencies;
import helperfoo.Converters;
import helperfoo.EnglishDeterminer;
import net.sf.extjwnl.JWNLException;

/**
 * @author Varun Singh
 *         Written in 2019
 */
public class MonoalphabeticCipher extends Cipher {
	List<Character> solvedLetters = new ArrayList<Character>();
	List<Character> replacedLetters = new ArrayList<Character>();

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
	 * @param ciphertext
	 *            the cipher that is deciphered
	 * @return the completely deciphered or almost completely deciphered
	 *         monoalphabetic substitution cipher in plaintext
	 */
	public String decrypt() {
		if (getText().isBlank())
			return "";
		Frequencies f = new Frequencies(this); // Defines a new Frequencies object
		System.out.println(getText());
		try {
			if (EnglishDeterminer.isSentence(this.getWords())) {
				return getText();
			}
		} catch (JWNLException j) {
			j.printStackTrace();
			return "An API error occured. ";
		}

		int m = 4;

		// Two Letter Words
		System.out.println("---------  TWO LETTER WORDS ---------------");
		solveFrequencyTypes(AlphabeticalStatistics.TWO_LETTER_WORDS, f.getMostFrequentNLetterWord(2));
		for (int i = 2; i < m; i++) {
			solveFrequencyTypes(AlphabeticalStatistics.TWO_LETTER_WORDS, f.getNMostFrequentNLetterWord(2, i));
		}

		// Three Letter Words
		System.out.println("---------  THREE LETTER WORDS -------------");
		solveFrequencyTypes(AlphabeticalStatistics.THREE_LETTER_WORDS, f.getMostFrequentNLetterWord(3));
		for (int i = 2; i < m; i++) {
			solveFrequencyTypes(AlphabeticalStatistics.THREE_LETTER_WORDS, f.getNMostFrequentNLetterWord(3, i));
		}
		// Four Letter Words
		System.out.println("---------  FOUR LETTER WORDS --------------");
		solveFrequencyTypes(AlphabeticalStatistics.FOUR_LETTER_WORDS, f.getMostFrequentNLetterWord(4));
		for (int i = 2; i < m; i++) {
			solveFrequencyTypes(AlphabeticalStatistics.FOUR_LETTER_WORDS, f.getNMostFrequentNLetterWord(4, i));
		}

		// Trigraphs
		System.out.println("---------  TRIGRAPHS         --------------");
		solveFrequencyTypes(AlphabeticalStatistics.TRIGRAPHS, f.getMostFrequentNGraph(3));
		for (int i = 2; i < m; i++)
			solveFrequencyTypes(AlphabeticalStatistics.TRIGRAPHS, f.getNMostFrequentNGraph(3, i));

		// Digraphs
		System.out.println("---------  DIGRAPHS          --------------");
		solveFrequencyTypes(AlphabeticalStatistics.DIGRAPHS, f.getMostFrequentNGraph(2));
		for (int i = 2; i < m; i++) {
			solveFrequencyTypes(AlphabeticalStatistics.DIGRAPHS, f.getNMostFrequentNGraph(2, i));
		}
		// Vowels/Social letters
		System.out.println("---------  SOCIAL LETTERS ---------------");
		solveFrequencyTypes(AlphabeticalStatistics.SOCIAL_LETTERS, f.getMostSocialLetter());
		for (int i = 2; i < m; i++)
			solveFrequencyTypes(AlphabeticalStatistics.SOCIAL_LETTERS, f.getNMostSocialLetter(i));

		// Initial letters
		System.out.println("---------  INITIAL LETTERS ----------------");
		solveFrequencyTypes(AlphabeticalStatistics.INITIAL_LETTERS, f.getMostFrequentPositionLetter(0));
		for (int i = 2; i < m; i++)
			solveFrequencyTypes(AlphabeticalStatistics.INITIAL_LETTERS, f.getNMostFrequentPositionLetter(0, i));

		// Double Letters
		System.out.println("---------  DOUBLE LETTERS    --------------");
		solveFrequencyTypes(AlphabeticalStatistics.DOUBLE_LETTERS, f.getMostFrequentDoubles());
		for (int i = 2; i < m; i++)
			solveFrequencyTypes(AlphabeticalStatistics.DOUBLE_LETTERS, f.getNMostFrequentDoubles(i));
		// Final letters
		System.out.println("---------  FINAL LETTERS ------------------");
		solveFrequencyTypes(AlphabeticalStatistics.FINAL_LETTERS, f.getMostFrequentPositionLetter(-3));
		for (int i = 2; i < m; i++)
			solveFrequencyTypes(AlphabeticalStatistics.FINAL_LETTERS, f.getNMostFrequentPositionLetter(-3, i));

		// "Random"
		System.out.println("---------  GUESS AND CHECK --------------");
		testRandom();

		System.out.println(replacedLetters);
		System.out.println(solvedLetters);

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

			return Character.toUpperCase(cipherAlphabet.get(nthLetter));
		}).collect(Collectors.toList());

		return Converters.convertListToString(cipherchars);
	}

	/**
	 * 
	 * @param toReplace
	 * @param replacement
	 */
	protected boolean replaceLetters(char toReplace, char replacement) {
		return replaceLetters(toReplace, replacement, replacement, toReplace);
	}

	/**
	 * 
	 * @param toReplace
	 * @param replacement
	 * @param toAdd
	 * @param toAdd2
	 */
	protected boolean replaceLetters(char toReplace, char replacement, char toAdd, char toAdd2) {
		if (replacedLetters.contains(toAdd2) || solvedLetters.contains(toAdd))
			return false;
		return isCorrect(toReplace, replacement, toAdd, toAdd2);
	}

	private boolean isCorrect(char toReplace, char replacement, char toAdd, char toAdd2) {
		String newText = getText().replace(toReplace, replacement);
		if (Arrays.stream(newText.split(" "))
				.filter(w -> {
					return w.contains(Character.toString(replacement)) && AlphabeticalStatistics.needsNoLetters(w);
				})
				.noneMatch(w -> {
					return !EnglishDeterminer.isWord(w);
				})) {
			setText(newText);
			solvedLetters.add(toAdd);
			replacedLetters.add(toAdd2);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public String magic() {
		return null;
	}

	/**
	 * 
	 */
	public void printCipherAlphabetAsTable() {

	}

	/**
	 * Find the words that require one (uppercase) letter to be solved
	 * Loop through the alphabet backwards until replacing the uppercase letter with the current
	 * alphabet creates an English word in the WordNet library
	 * Validate replacing the old char with the new one
	 * If valid, replace the letter and print the replacement
	 * Replace again if necessary
	 * End the for loop and move on to the next word, if there is one
	 */
	public void testRandom() {
		List<Character> modifiableCollection = new ArrayList<Character>(
				Arrays.asList(AlphabeticalStatistics.ALL_LETTERS));
		for (int i = 0; i < this.getWords().length; i++) {
			String w = this.getWords()[i];
			if (AlphabeticalStatistics.needsOneLetter(w)) {
				char oldChar = w.charAt(getIndexOfFirstLowerCase(w));
				if (!solvedLetters.contains(oldChar)) {
					for (int j = 0; j < modifiableCollection.size(); j++) { // Loop through letters of alphabet
						Character letterOfAlphabet = modifiableCollection.get(j);
						if (replaceLetters(oldChar, letterOfAlphabet)) {
							modifiableCollection.remove(letterOfAlphabet);
							System.out.println(oldChar + " was replaced with " + letterOfAlphabet.toString());
							break;
						}
					}

				}
			}
		}
	}

	private int getIndexOfFirstLowerCase(String w) {
		int index = 0;
		for (char c : w.toCharArray()) {
			if (Character.isUpperCase(c))
				break;
			index++;
		}

		return index;
	}

	/**
	 * Overload for position letters
	 * Runs all needed conditions and loops to replace letters and ultimately use frequency analysis
	 * algorithms
	 * <br>
	 * A frequency type is the frequency of a property or position of words and letters
	 * 
	 * @param c
	 *            The array from AlphabeticalStatistics.java from the alphastats package containing the
	 *            order of frequencies of a type of letter
	 * @param p
	 *            The Pair that is replacing another letter in the ciphertext with the highest of the
	 *            frequency type
	 * @example Initial letters - c = AlphabeticalStatistics.INITIAL_LETTERS, p =
	 *          mostFrequentInitialLetter
	 * @invokes replaceLetters(String, String)
	 */
	private void solveFrequencyTypes(char[] c, String s) {
		if (s.isBlank() || EnglishDeterminer.isInteger(s)) {
			return;
		}
		char replacedLetter = s.charAt(0);
		for (char solvedLetter : c) {
			if (replaceLetters(replacedLetter, solvedLetter)) {
				System.out.println(
						Character.toString(replacedLetter) + " was replaced with " + Character.toString(solvedLetter));
				return;
			}
		}
	}

	/**
	 * 
	 * @param c
	 *            the array of the most frequent n-letter words or n-graphs from AlphabeticalStatistics
	 * @param s
	 *            The String of the most common n-letter word or n-graph in the text; being replaced
	 */
	private void solveFrequencyTypes(String[] c, String s) {
		if (s.isBlank() || EnglishDeterminer.isInteger(s)) {
			return;
		}
		for (String nLetterWord : c) { // Loop through the constant array
			for (char v : nLetterWord.toCharArray()) {
				if (solvedLetters.contains(v)) {
					break;
				}
			}
			if (!s.equals(s.toUpperCase())) {
				List<Integer> n = AlphabeticalStatistics.getLowers(s);
				if (n.stream().anyMatch(g -> s.charAt(g) != nLetterWord.charAt(g))) {
					continue;
				}
			}
			boolean b = false;
			int i = 0;
			do {
				char solvedLetter = nLetterWord.charAt(i);
				char replacedLetter = s.charAt(i);
				if (replaceLetters(replacedLetter, solvedLetter))
					b = true;
				i++;
			} while (b && i < s.length());
			if (b) {
				System.out.println(s + " was replaced with " + nLetterWord);
				return;
			}
		}
	}
}
