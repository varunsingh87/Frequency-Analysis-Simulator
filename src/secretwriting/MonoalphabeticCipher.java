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
import helperfoo.Pair;

public class MonoalphabeticCipher extends Cipher {
	List<String> solvedLetters = new ArrayList<String>();
	List<String> replacedLetters = new ArrayList<String>();
	
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
		Frequencies f = new Frequencies(this); // Defines a new Frequencies object
		System.out.println(getText());	
		
		// One Letter Words
		solveFrequencyTypes(AlphabeticalStatistics.ONE_LETTER_WORDS, f.getMostFrequentNLetterWord(1));
		// Trigraphs
		solveFrequencyTypes(AlphabeticalStatistics.TRIGRAPHS, f.getMostFrequentNGraph(3));
		// Four Letter Words
		solveFrequencyTypes(AlphabeticalStatistics.FOUR_LETTER_WORDS, f.getMostFrequentNLetterWord(4));
		// Digraphs
		solveFrequencyTypes(AlphabeticalStatistics.DIGRAPHS, f.getMostFrequentNGraph(2));
		// Double Letters
		solveFrequencyTypes(AlphabeticalStatistics.DOUBLE_LETTERS, f.getMostFrequentDoubles());
		// Random
		testRandom();
		// Final letters
		solveFrequencyTypes(AlphabeticalStatistics.FINAL_LETTERS, f.getMostFrequentPositionLetter(-3));
		// Initial letters
		solveFrequencyTypes(AlphabeticalStatistics.INITIAL_LETTERS, f.getMostFrequentPositionLetter(0));
		// Three Letter Words
		solveFrequencyTypes(AlphabeticalStatistics.THREE_LETTER_WORDS, f.getMostFrequentNLetterWord(3));		
		// Two Letter Words
		solveFrequencyTypes(AlphabeticalStatistics.TWO_LETTER_WORDS, f.getMostFrequentNLetterWord(2));
		// Vowels/Social letters
		solveFrequencyTypes(AlphabeticalStatistics.SOCIAL_LETTERS, f.getMostSocialLetter());		
		// All letters
		
		System.out.println(replacedLetters.toString());
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

			return Character.toUpperCase(cipherAlphabet.get(nthLetter));
		}).collect(Collectors.toList());

		return Converters.convertListToString(cipherchars);
	}
	
	/**
	 * 
	 * @param toReplace
	 * @param replacement
	 */
	private boolean replaceLetters(String toReplace, String replacement) {
		return replaceLetters(toReplace, replacement, replacement, toReplace);
	}
	
	/**
	 * 
	 * @param toReplace
	 * @param replacement
	 * @param toAdd
	 */
	@SuppressWarnings("unused")
	private void replaceLetters(String toReplace, String replacement, String toAdd) {
		replaceLetters(toReplace, replacement, toAdd, toReplace);
	}
	
	/**
	 * 
	 * @param toReplace
	 * @param replacement
	 * @param toAdd
	 * @param toAdd2
	 */
	private boolean replaceLetters(String toReplace, String replacement, String toAdd, String toAdd2) {
		for (int i = 0; i < replacedLetters.size(); i++) {
			if (toAdd2.contains(replacedLetters.get(i))) {
				return false;
			}
		}
		for (int i = 0; i < solvedLetters.size(); i++) {
			if(toAdd.contains(solvedLetters.get(i))) {
				return false;
			}
		}
		setText(getText().replace(toReplace, replacement));
		solvedLetters.add(toAdd);
		replacedLetters.add(toAdd2);
		return true;
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
	 * 
	 */
	public void testRandom() {
		Arrays.stream(this.getWords()).filter(w -> AlphabeticalStatistics.needsOneLetter(w)).forEach(w -> {
			System.out.println(w);
			char oldChar = w.charAt(getIndexOfFirstLowerCase(w));
			for (int i = 0; i < EnglishDeterminer.ALPHABET.size(); i++) {
				Character letterOfAlphabet = EnglishDeterminer.ALPHABET.get(i);
				String loaAsString = letterOfAlphabet.toString();
				if (EnglishDeterminer.isWord(w.replace(oldChar, letterOfAlphabet))) {
					if (replaceLetters(Character.toString(oldChar), loaAsString)) {
						System.out.println(oldChar + " was replaced with " + loaAsString);
						break;
					}					
				}
			}
		});
	}
	
	private int getIndexOfFirstLowerCase(String w) {
		int index = 0;
		for(char c : w.toCharArray()) {
		   if (Character.isUpperCase(c)) break;
		   index++;
		}
		
		return index;
	}
	
	/**  
	 * Overload for position letters
	 * Runs all needed conditions and loops to replace letters and ultimately use frequency analysis algorithms
	 * <br>
	 * A frequency type is the frequency of a property or position of words and letters 
	 * @param c The array from AlphabeticalStatistics.java from the alphastats package containing the order of frequencies of a type of letter
	 * @param p The Pair that is replacing another letter in the ciphertext with the highest of the frequency type 
	 * @example Initial letters - c = AlphabeticalStatistics.INITIAL_LETTERS, p = mostFrequentInitialLetter
	 * @invokes replaceLetters(String, String)
	 */
	private void solveFrequencyTypes(char[] c, Pair p) {
		char notSolvedTypeLetter = ' ';
		for (char typeLetter : c) {
			if (!solvedLetters.contains(Character.toString(typeLetter))) {
				notSolvedTypeLetter = Character.toLowerCase(typeLetter);
			}
		}
		
		if (notSolvedTypeLetter != ' ') {
			replaceLetters(p.props, Character.toString(notSolvedTypeLetter));
			System.out.println("The common position letter " + p.props + " in the ciphertext was replaced with " + notSolvedTypeLetter);
		}
	}
	
	/**
	 * Overload for n-grams
	 * @param c array contains the order of n-grams by frequency for each number n
	 * @param d array of the most common of a frequency type
	 */
	private void solveFrequencyTypes(String[] c, char[] d) {
		String notSolvedTypePhrase = "";
		for (String nGram : c) {
			boolean contained = false;
			int f = 0;
			while (!contained && f < d.length) {
				if (solvedLetters.contains(Character.toString(d[f])))
					contained = true;
				f++;
			}
			if (!contained) {
				notSolvedTypePhrase = nGram;			
				break;
			}
		}
		
		if (!notSolvedTypePhrase.equals("")) {
			for (int i = 0; i <= d.length - 1; i++) {
				char solvedLetter = notSolvedTypePhrase.charAt(i);
				char replacedLetter = d[i];
				
				replaceLetters(Character.toString(replacedLetter), Character.toString(solvedLetter));
			}
			System.out.println("The common n-graph " + String.valueOf(d) + " in the ciphertext was replaced with " + notSolvedTypePhrase);
		}
	}
	
	/**
	 * 
	 * @param c the array of the most frequent n-letter words
	 * @param s The String of the most common n-letter word in the text; being replaced
	 */
	private void solveFrequencyTypes(String[] c, String s) {
		String notSolvedTypeWord = "";
		for (String nLetterWord : c) { // Loop through the constant array
			boolean contained = false;
			int f = 0;
			while (!contained && f < s.length()) { // Loops through s
				if (solvedLetters.contains(Character.toString(s.charAt(f))))
					contained = true;
				f++;
			}
			if (!contained) {
				notSolvedTypeWord = nLetterWord;
				System.out.println(s + " was replaced with " + notSolvedTypeWord);
				break;
			}
		}
		
		if (!notSolvedTypeWord.equals("")) {
			for (int i = 0; i < s.length(); i++) {
				char solvedLetter = notSolvedTypeWord.charAt(i);
				char replacedLetter = s.charAt(i);
				System.out.println(replacedLetter);
				replaceLetters(Character.toString(replacedLetter), Character.toString(solvedLetter));
			}
			System.out.println("The common frequency type " + s + " in the ciphertext was replaced with " + notSolvedTypeWord);
		}
	}
}
