package secretwriting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import alphastats.AlphabeticalStatistics;
import helperfoo.Converters;
import helperfoo.EnglishDeterminer;
import helperfoo.Pair;
import net.sf.extjwnl.JWNLException;

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
		
		Pair mostFrequentFinalLetter = getMostFrequentFinalLetter();
		Pair vowel = getMostSocialLetter();
		Pair threeLetterWord = getMostFrequentTrigraph();
		
		setText(replaceBigrams());
		setText(getText().replace(mostFrequentFinalLetter.props, "s"));
		setText(getText().replace(vowel.props, "a"));
		setText(getText().replace(threeLetterWord.props, "the"));
		
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
		// TODO Auto-generated method stub
		return null;
	}

	public void printCipherAlphabetAsTable() {
		// TODO Auto-generated method stub

	}
	
	protected Pair getMostFrequentFinalLetter() {
		Comparator<Pair> cmp = Comparator.comparing(p -> (long)p.val);
		return Collections.max(getLetterFinalities(), cmp);
	}

	private List<Pair> getLetterFinalities() {
		String[] words = getWords();
		List<Character> finalLetters = new ArrayList<Character>();
		for (String word : words) {
			finalLetters.add(word.charAt(word.length() - 1));
		}

		List<Pair> finalLetterOccurences = new ArrayList<Pair>();
		for (char letter : EnglishDeterminer.ALPHABET) {
			Pair pair = new Pair(Character.toUpperCase(letter), finalLetters.stream().filter(l -> l.equals(Character.toUpperCase(letter))).count());
			finalLetterOccurences.add(pair);
		}
		
		System.out.println(Arrays.toString(finalLetterOccurences.toArray()));
		
		return finalLetterOccurences;
		

	}

	protected Pair getMostSocialLetter() {
		Comparator<Pair> cmp = Comparator.comparing(p -> (int) p.val);
		return Collections.max(getLetterSocialities(), cmp);
	}

	/**
	 * Returns the "sociality" of each letter in an ArrayList of Pair
	 * <p>
	 * Sociality is defined as how many times other letters are located next to a
	 * certain letter at least once. If the sociality is high, it is extremeloy
	 * likely that the
	 * </p>
	 * 
	 * @return a list of the sociality of each letter as an int
	 */
	private List<Pair> getLetterSocialities() {
		List<Pair> socialLetters = new ArrayList<Pair>(); // Initialize list for letters
		for (char letter : EnglishDeterminer.ALPHABET) {
			letter = Character.toUpperCase(letter);
			int meetings = 0; // The number of letters that it meets up with at least once
			for (char letter2 : EnglishDeterminer.ALPHABET) {
				letter2 = Character.toUpperCase(letter2);

				// Increment meetings if letter 1 meets up with letter 2
				if (getOccurences("" + letter + letter2) >= 1) {
					meetings++;
				}
			}
			// Add the letter and its meetings to the social digraphs list
			socialLetters.add(new Pair(letter, meetings));
		}
		
		System.out.println(Arrays.toString(socialLetters.toArray()));
		
		return socialLetters;
	}
	
	protected String replaceBigrams() {
		String ciphertext1 = "";
		List<String> goodWords = getThreeLettersWithDoubles();
		for (String word : goodWords) {
			for (Character c : AlphabeticalStatistics.DOUBLE_LETTERS) {
				char doub = AlphabeticalStatistics.doubleLetterInWord(word);
				String word1 = word.replace(doub, c);
				
				try {
					if (EnglishDeterminer.isWord(word1) ) {
						ciphertext1 = getText().replace(word, word1.toLowerCase());
					}	
				} catch (JWNLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return ciphertext1;
	}

	/**
	 * Deciphers certain letters based on their bigrams and double letters
	 * @param ciphertext
	 * @param listOfDifferences
	 * @return text with the letters that have been deciphered replaced with lowercase plaintext letters
	 */
	private List<String> getThreeLettersWithDoubles() {
		// Get all words that are three letters and have double letters in them 
		return Arrays.asList(getWords())
				.stream()
				.filter(
					word -> {
						System.out.println(word);
						return AlphabeticalStatistics.meetsAllConditions(
								AlphabeticalStatistics.isNLetters(
										word, 3), 
								AlphabeticalStatistics.hasDoubleInWord(
										word)
						);
					}
				)
				.collect(
					Collectors.toList()
				);

	}
	
	protected Pair getMostFrequentTrigraph() {
		Comparator<Pair> cmp = Comparator.comparing(p -> (long) p.val);
		return Collections.max(getThreeLetterWordOccurences(), cmp);
	}
	
	private List<Pair> getThreeLetterWordOccurences() {
		// Get all words that are three letters long to try out the most common three letter words
		List<Pair> threeLetterWords = new ArrayList<Pair>();
		// Invoke methods upon the text in order to iterate through each word (without returning anything)
		Arrays.asList(getWords()).stream().forEach(w -> {
			// If w is a three letter word
			Pair p = new Pair(w, getOccurences(w));
			boolean pairExists = threeLetterWords.contains(p);
			System.out.println(p.toString() + " " + pairExists);
			if (AlphabeticalStatistics.meetsAllConditions(AlphabeticalStatistics.isNLetters(w, 3)) && !pairExists)
				threeLetterWords.add(new Pair(w, getOccurences(w)));
		});
		
		System.out.println(Arrays.toString(threeLetterWords.toArray()));
		
		return threeLetterWords;
	}

}
