package alphastats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import helperfoo.EnglishDeterminer;
import helperfoo.Pair;
import net.sf.extjwnl.JWNLException;
import secretwriting.Cipher;

/**
 * Contains methods based on Simon Singh's webpage, The Black Chamber:  
 * {@link https://simonsingh.net/The_Black_Chamber/hintsandtips.html}
 * @structure private method and public method
 * @purpose applications that involve frequency analysis
 */
public final class Frequencies {
	public Cipher cipher;
	
	// Define constructor
	public Frequencies(Cipher ci) {
		cipher = ci;
	}
	
	public Pair getMostFrequentFinalLetter() {
		return Arrays.stream(getLetterFinalities()).max(new Comparator<Pair>() {
			public int compare(Pair pair1, Pair pair2) {
				return pair1.compareToLong(pair2);
			}
		}).get();
	}

	private Pair[] getLetterFinalities() {
		String[] words = cipher.getWords();
		List<Character> finalLetters = new ArrayList<Character>();
		for (String word : words) {
			finalLetters.add(word.charAt(word.length() - 1));
		}
	
		Pair[] finalLetterOccurences = new Pair[26];
		for (char letter : EnglishDeterminer.ALPHABET) {
			Pair pair = new Pair(Character.toUpperCase(letter), finalLetters.stream().filter(l -> l.equals(Character.toUpperCase(letter))).count());
			finalLetterOccurences[EnglishDeterminer.ALPHABET.indexOf(letter)] = pair;
		}
		
		System.out.println(Arrays.toString(finalLetterOccurences));
		
		return finalLetterOccurences;
		
	
	}

	public Pair getMostSocialLetter() {
		return Arrays.stream(getLetterSocialities()).max(new Comparator<Pair>() {
			public int compare(Pair pair1, Pair pair2) {
				return pair1.compareToInt(pair2);
			}
		}).get();
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
	private Pair[] getLetterSocialities() {
		Pair[] socialLetters = new Pair[26]; // Initialize list for letters
		for (char letter : EnglishDeterminer.ALPHABET) {
			char letterUp = Character.toUpperCase(letter);
			int meetings = 0; // The number of letters that it meets up with at least once
			for (char letter2 : EnglishDeterminer.ALPHABET) {
				letter2 = Character.toUpperCase(letter2);
	
				// Increment meetings if letter 1 meets up with letter 2
				if (cipher.getOccurences("" + letterUp + letter2) >= 1) {
					meetings++;
				}
			}
			// Add the letter and its meetings to the social digraphs list
			int i = EnglishDeterminer.ALPHABET.indexOf(letter);
			socialLetters[i] = new Pair(letter, meetings);
		}
		
		return socialLetters;
	}

	public String replaceBigrams() {
		String ciphertext1 = "";
		String[] goodWords = getThreeLettersWithDoubles();
		for (String word : goodWords) {
			for (Character c : AlphabeticalStatistics.DOUBLE_LETTERS) {
				char doub = AlphabeticalStatistics.doubleLetterInWord(word);
				String word1 = word.replace(doub, c);
				
				try {
					if (EnglishDeterminer.isWord(word1) ) {
						ciphertext1 = cipher.getText().replace(word, word1.toLowerCase());
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
	private String[] getThreeLettersWithDoubles() {
		// Get all words that are three letters and have double letters in them 
		return Arrays.stream(cipher.getWords()).filter(
					word -> {
						return AlphabeticalStatistics.meetsAllConditions(
								AlphabeticalStatistics.isNLetters(
										word, 3), 
								AlphabeticalStatistics.hasDoubleInWord(
										word)
						);
					}
				).toArray(String[]::new);
	
	}

	public Pair getMostFrequentTrigraph() {
		Comparator<Pair> cmp = Comparator.comparing(p -> (long) p.val);
		return Collections.max(getThreeLetterWordOccurences(), cmp);
	}

	private List<Pair> getThreeLetterWordOccurences() {
		// Get all words that are three letters long to try out the most common three letter words
		List<Pair> threeLetterWords = new ArrayList<Pair>();
		// Invoke methods upon the text in order to iterate through each word (without returning anything)
		Arrays.asList(cipher.getWords()).stream().forEach(w -> {
			// If w is a three letter word
			Pair p = new Pair(w, cipher.getOccurences(w));
			boolean pairExists = threeLetterWords.contains(p);
			
			if (AlphabeticalStatistics.meetsAllConditions(AlphabeticalStatistics.isNLetters(w, 3)) && !pairExists)
				threeLetterWords.add(new Pair(w, cipher.getOccurences(w)));
		});
		
		List<Pair> newThreeLetterWords = threeLetterWords.stream().distinct().collect(Collectors.toList());
		
		System.out.println(Arrays.toString(newThreeLetterWords.toArray()));
		
		return newThreeLetterWords;
	}
	
	public Pair getMostFrequentInitialLetter() {
		return Arrays.stream(getLetterInitialities()).max(new Comparator<Pair>() {
			public int compare(Pair pair1, Pair pair2) {
				return pair1.compareToLong(pair2);
			}
		}).get();
	}
	
	/**
	 * 'Initialities' as defined in this context - the number of occurences for a given letter as it appears at the beginning of a word
	 * 
	 * @return
	 */
	private Pair[] getLetterInitialities() {
		String[] words = cipher.getWords(); // Get cipher as a String[] split into words
		List<Character> initialLetters = new ArrayList<Character>();
		for (String word : words) {
			initialLetters.add(word.charAt(0));
		}
	
		Pair[] initialLetterOccurences = new Pair[26];
		for (int i = 0; i < 26; i++) { // Give each element in the array a value
			char letter = EnglishDeterminer.ALPHABET.get(i);
			Pair pair = new Pair(Character.toUpperCase(letter), initialLetters.stream().filter(l -> l.equals(Character.toUpperCase(letter))).count());
			initialLetterOccurences[i] = pair;
		}
		
		System.out.println(Arrays.toString(initialLetterOccurences));
		
		return initialLetterOccurences;
		
	
	}
	
}
