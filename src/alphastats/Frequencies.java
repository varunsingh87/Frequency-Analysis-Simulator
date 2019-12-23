package alphastats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import helperfoo.EnglishDeterminer;
import helperfoo.Pair;
import secretwriting.Cipher;

/**
 * Contains methods based on Simon Singh's webpage, The Black Chamber:  
 * {@link https://simonsingh.net/The_Black_Chamber/hintsandtips.html}
 * @structure private method and public method;
 * Private method gets a collection of some sort;
 * Public method aggregates private method to get the highest value
 * @purpose applications that involve frequency analysis
 */
public final class Frequencies {
	public Cipher cipher;
	
	// Define constructor
	public Frequencies(Cipher ci) {
		cipher = ci;
	}
	
	public String getText() {
		return cipher.getText();
	}
	
	/**
	 * 
	 * @param x the indicator as to whether to get the first letter or the last letter
	 * @return the Pair[] of all the position letters and their respective occurence counts in the given text
	 */
	private Pair[] getPositionLetterData(int x) {
		String[] words = cipher.getWords();
		List<Character> positionLetters = new ArrayList<Character>();
		
		for (String word : words) {
			if (x == 0) {
				positionLetters.add(word.charAt(x));
			} else {
				positionLetters.add(word.charAt(word.length() - 1));
			}
		}
		
		Pair[] positionLetterOccurences = new Pair[26];
		for (int i = 0; i < 26; i++) {
			char letter = EnglishDeterminer.ALPHABET.get(i);
			Pair p = new Pair(Character.toUpperCase(letter), positionLetters.stream().filter(l -> l.equals(Character.toUpperCase(letter))).count());
			positionLetterOccurences[EnglishDeterminer.ALPHABET.indexOf(letter)] = p;
		}
		
		System.out.println("Letter positionities: " + Arrays.toString(positionLetterOccurences));
		
		return positionLetterOccurences;
	}
	
	public Pair getMostFrequentPositionLetter(int x) {
		return Arrays.stream(getPositionLetterData(x)).max(new Comparator<Pair>() {
			public int compare(Pair pair1, Pair pair2) {
				return pair1.compareToLong(pair2);
			}
		}).get();
	}

	/**
	 * 
	 * @param x the number of letters in the n gram that is used for some calculations
	 * @return the List<Pair> of all the NGrams and their respective occurence counts in the given text
	 */
	private List<Pair> getNGramOccurences(int x) {
		List<Pair> ngraphs = new ArrayList<Pair>();
		Arrays.stream(cipher.getWords()).forEach(w -> {
			for (int j = 0; j < w.length() - (x - 1); j++) {
				String ngraph = "";
				for (int i = 0; i < x; i++) {
					ngraph += w.charAt(j + i);
				}
				
				Pair p = new Pair(ngraph, new FrequencyHelpers(cipher.getText()).getOccurences(w));
				
				if (!ngraphs.contains(p)) {
					ngraphs.add(p);
				}
			}
		});
		
		System.out.println("N-graphs: " + Arrays.toString(ngraphs.toArray()));
		return ngraphs;
	}
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	public char[] getMostFrequentNGraph(int x) {
		Comparator<Pair> cmp = Comparator.comparing(p -> (long) p.val);
		Pair wouldBe = Collections.max(getNGramOccurences(x), cmp);
		char[] toReturn = new char[x];
		for (int i = 0; i < x; i++) {
			toReturn[i] = wouldBe.props.charAt(i);
		}
		
		return toReturn;
	}
	
	/** Overload of getMostFrequentNGraph()
	 * <ol>
	 * <li>Sorts the list using Collections.sort() and the Comparator class</li>
	 * <li>Gets the nth index of that array</li>
	 * <li>Stores each character of the digraph in a char[] which is returned</li>
	 * </ol>
	 * @param n an ordinal number from the most frequent digraph
	 * @return the digraph that has the nth largest amount of occurences
	 */
	public char[] getMostFrequentNGraph(int x, int n) {
		Comparator<Pair> cmp = Comparator.comparing(p -> (long) p.val);
		List<Pair> ngraphPairs = getNGramOccurences(x);
		
		Collections.sort(ngraphPairs, cmp);
		Pair wouldBe = ngraphPairs.get(n);
		char[] toReturn = new char[x];
		for (int i = 0; i < x; i++) {
			toReturn[i] = wouldBe.props.charAt(i);
		}
		
		return toReturn;
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
				if (new FrequencyHelpers(cipher.getText()).getOccurences("" + letterUp + letter2) >= 1) {
					meetings++;
				}
			}
			// Add the letter and its meetings to the social digraphs list
			int i = EnglishDeterminer.ALPHABET.indexOf(letter);
			socialLetters[i] = new Pair(letterUp, meetings);
		}
		
		System.out.println(Arrays.toString(socialLetters));
		
		return socialLetters;
	}

	public Pair getMostSocialLetter() {
		return Arrays.stream(getLetterSocialities()).max(new Comparator<Pair>() {
			public int compare(Pair pair1, Pair pair2) {
				return pair1.compareToInt(pair2);
			}
		}).get();
	}

	public String replaceBigrams() {
		String ciphertext1 = "";
		String[] goodWords = getThreeLettersWithDoubles();
		for (String word : goodWords) {
			for (Character c : AlphabeticalStatistics.DOUBLE_LETTERS) {
				char doub = AlphabeticalStatistics.doubleLetterInWord(word);
				String word1 = word.replace(doub, c);
				if (EnglishDeterminer.isWord(word1) ) {
					ciphertext1 = cipher.getText().replace(word, word1.toLowerCase());
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

	public Pair getMostFrequentThreeLetterWordWithDoubles() {
		return null;
	}

	/**
	 * 
	 * @param ciphertext
	 * @return
	 */
	public Object[][] getMostFrequentLetters(Object[][] listOfDifferences) {	
		
		int leastFrequentMostFrequentLetterFrequency = FrequencyHelpers.getLeastFrequentMostFrequentLetterFrequency(listOfDifferences);
		Object[][] mostFrequentLetters = Arrays.asList(listOfDifferences).stream().filter(a -> {
	
			return (long)a[1] >= leastFrequentMostFrequentLetterFrequency;
		}).toArray(Object[][]::new);
		
		return mostFrequentLetters;
	}

	public Object[][] getMostFrequentLetters() {
		Object[][] listOfDifferences = new FrequencyHelpers(cipher.getText()).getDifferencesOfOccurences();
		Object[][] mostFrequentLetters = getMostFrequentLetters(listOfDifferences);
		System.out.println(Arrays.deepToString(mostFrequentLetters));
		return mostFrequentLetters;
	}
}
