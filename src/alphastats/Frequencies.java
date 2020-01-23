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
	 * @return the Pair[] of all the position letters and their respective occurrence counts in the given text
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
			char letter = Character.toUpperCase(EnglishDeterminer.ALPHABET.get(i));
			positionLetterOccurences[i] = new Pair(letter, positionLetters.stream().filter(l -> l.equals(letter)).count());
		}
		
		System.out.println("Letter positionities: " + Arrays.toString(positionLetterOccurences));
		
		return positionLetterOccurences;
	}
	
	public String getMostFrequentPositionLetter(int x) {
		return Arrays.stream(getPositionLetterData(x)).max(new Comparator<Pair>() {
			public int compare(Pair pair1, Pair pair2) {
				return pair1.compareToLong(pair2);
			}
		}).get().props;
	}

	/**
	 * 
	 * @param x the number of letters in the n gram that is used for some calculations
	 * @return the List<Pair> of all the NGrams and their respective occurrence counts in the given text
	 */
	private List<Pair> getNGramOccurences(int x) {
		List<Pair> ngraphs = new ArrayList<Pair>();
		Arrays.stream(cipher.getWords()).distinct().forEach(w -> {
			for (int j = 0; j < w.length() - (x - 1); j++) {
				String ngram = w.substring(j, j + x);
				Pair p = new Pair(ngram, new FrequencyHelpers(cipher.getText()).getOccurences(ngram));
				ngraphs.add(p);
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
	public String getMostFrequentNGraph(int x) {
		Comparator<Pair> cmp = Comparator.comparing(p -> (long) p.val);
		Pair wouldBe = Collections.max(getNGramOccurences(x), cmp);
		return wouldBe.props;
	}
	
	/** Overload of getMostFrequentNGraph()
	 * <ol>
	 * <li>Sorts the list using Collections.sort() and the Comparator class</li>
	 * <li>Gets the nth index of that array</li>
	 * <li>Stores each character of the digraph in a char[] which is returned</li>
	 * </ol>
	 * @param x the number of letters in the n graph
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
	
	public String getMostFrequentNLetterWord(int n) {
		return Arrays.stream(cipher.getWords()).map(w -> {
			if (AlphabeticalStatistics.isNLetters(w, n)) {
				String withRemovals = EnglishDeterminer.removeSpacesAndPunctuation(w);
				return new Pair(withRemovals, new FrequencyHelpers(getText()).getOccurences(withRemovals));
			}
			
			return new Pair("", (long)0);
		}).max(Comparator.comparing(p -> (long) p.val)).get().props;
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

	public String getMostSocialLetter() {
		return Arrays.stream(getLetterSocialities()).max(new Comparator<Pair>() {
			public int compare(Pair pair1, Pair pair2) {
				return pair1.compareToInt(pair2);
			}
		}).get().props;
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
	
	public String getMostFrequentDoubles() {
		return Arrays.stream(cipher.getWords()).filter(w -> {
			return AlphabeticalStatistics.hasDoubleInWord(w);
		}).distinct().map(w -> {
			String dword = AlphabeticalStatistics.doubleLetterInWord(w);
			return new Pair(dword, new FrequencyHelpers(cipher.getText()).getOccurences(dword));
		}).max(Comparator.comparing(p -> (long) p.val)).get().props;
	}
}
