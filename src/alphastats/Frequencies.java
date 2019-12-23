package alphastats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import helperfoo.Converters;
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
	
	public Pair getMostFrequentFinalLetter() {
		return Arrays.stream(getPositionLetterData(-3)).max(new Comparator<Pair>() {
			public int compare(Pair pair1, Pair pair2) {
				return pair1.compareToLong(pair2);
			}
		}).get();
	}
	
	public Pair getMostFrequentInitialLetter() {
		return Arrays.stream(getPositionLetterData(0)).max(new Comparator<Pair>() {
			public int compare(Pair pair1, Pair pair2) {
				return pair1.compareToLong(pair2);
			}
		}).get();
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
				
				Pair p = new Pair(ngraph, getOccurences(w));
				
				if (!ngraphs.contains(p)) {
					ngraphs.add(p);
				}
			}
		});
		
		System.out.println("N-graphs: " + Arrays.toString(ngraphs.toArray()));
		return ngraphs;
	}

	public char[] getFrequentDigraph() {
		Comparator<Pair> cmp = Comparator.comparing(p -> (long) p.val);
		Pair wouldBe = Collections.max(getNGramOccurences(2), cmp);
		char[] toReturn = new char[2];
		toReturn[0] = wouldBe.props.charAt(0);
		toReturn[1] = wouldBe.props.charAt(1);
		
		return toReturn;
	}

	/** Overload of getFrequentDigraph()
	 * <ol>
	 * <li>Sorts the list using Collections.sort() and the Comparator class</li>
	 * <li>Gets the nth index of that array</li>
	 * <li>Stores each character of the digraph in a char[] which is returned</li>
	 * </ol>
	 * @param n an ordinal number from the most frequent digraph
	 * @return the digraph that has the nth largest amount of occurences
	 */
	public char[] getFrequentDigraph(int n) {
		List<Pair> digraphPairs = getNGramOccurences(2);
		Comparator<Pair> cmp = Comparator.comparing(p -> (long) p.val);
		Collections.sort(digraphPairs, cmp);
		Pair wouldBe = digraphPairs.get(n);
		char[] toReturn = new char[2];
		toReturn[0] = wouldBe.props.charAt(0);
		toReturn[1] = wouldBe.props.charAt(1);
		
		return toReturn;
	}

	public char[] getMostFrequentTrigraph() {
		Comparator<Pair> cmp = Comparator.comparing(p -> (long) p.val);
		Pair wouldBe = Collections.max(getNGramOccurences(3), cmp);
		char[] toReturn = new char[3];
		toReturn[0] = wouldBe.props.charAt(0);
		toReturn[1] = wouldBe.props.charAt(1);
		toReturn[2] = wouldBe.props.charAt(2);
		
		return toReturn;
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
				if (getOccurences("" + letterUp + letter2) >= 1) {
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
	
	@SuppressWarnings("unused")
	private void bigramMethod() {
		
	}

	public Pair getMostFrequentThreeLetterWordWithDoubles() {
		return null;
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
	
	/** 
	 * Get the number of occurences of a given letter in a given text
	 * @param letter the character that is being counted
	 * @return the number of occurences of the letter as a long
	 */
	public long getOccurences(char letter) {
		long frequencyOfLetter = Converters.convertStringToListOfCharacters(getText()).stream().filter(e -> {
			return e.equals(letter);
		}).count();
		return frequencyOfLetter;
	}
	
	/**
	 * Get the number of occurences of a given n-gram in a given text
	 * @param letters the set of alphabetical characters that is being counted
	 * @return the number of occurences of @param letters in the text
	 */
	public long getOccurences(String letters) {
		long frequencyOfLetter = Converters.convertStringToListOfStrings(getText()).stream().filter(e -> {
			return e.contains(letters);
		}).count();
		return frequencyOfLetter;
	}
	
	/**
	 * 
	 * @param ciphertext
	 * @return
	 */
	public Object[][] getMostFrequentLetters(Object[][] listOfDifferences) {	
		
		int leastFrequentMostFrequentLetterFrequency = getLeastFrequentMostFrequentLetterFrequency(listOfDifferences);
		Object[][] mostFrequentLetters = Arrays.asList(listOfDifferences).stream().filter(a -> {
	
			return (long)a[1] >= leastFrequentMostFrequentLetterFrequency;
		}).toArray(Object[][]::new);
		
		return mostFrequentLetters;
	}

	public Object[][] getMostFrequentLetters() {
		Object[][] listOfDifferences = getDifferencesOfOccurences();
		Object[][] mostFrequentLetters = getMostFrequentLetters(listOfDifferences);
		System.out.println(Arrays.deepToString(mostFrequentLetters));
		return mostFrequentLetters;
	}

	/**
	 * @param text
	 * @return
	 */
	protected Object[][] getListOfOccurences() {
		Stream<Long> alphabetCollection = EnglishDeterminer.ALPHABET.stream().map(l -> {
			return getOccurences(l);
		});
		
		List<Long> listOfOccurences = alphabetCollection.collect(Collectors.toList());
		ArrayList<ArrayList<Object>> letterOccurencesPairs = new ArrayList<ArrayList<Object>>();
		for (int i = 0; i < EnglishDeterminer.ALPHABET.size(); i++) {
			ArrayList<Object> letterOccurencesPair = new ArrayList<Object>();
			// First element is letter as Character
			letterOccurencesPair.add(EnglishDeterminer.ALPHABET.get(i)); 
			// Second element is number of occurences as Long
			letterOccurencesPair.add(listOfOccurences.get(i)); 
			// Third element is to be set as difference between the index 1 and index 1 of the previous array

			// Add the array list to the outer array list
			letterOccurencesPairs.add(letterOccurencesPair);
		}
		
		Object[][] letterOccurencesPairs1 = Converters.toMultiDimensionalArray(letterOccurencesPairs);
		
		return letterOccurencesPairs1;
		
	}
	
	/**
	 * Sort the 2-D array based on the number of occurences
	 * @param text the ciphertext that the occurences are being counted from
	 * @return the sorted Object[][]
	 */
	protected Object[][] getSortedListOfOccurences() {
		
		Object[][] listOfOccurences = getListOfOccurences();
		
		// Sort the 2 dimensional array into smallest to largest by number of occurences
		Arrays.sort(listOfOccurences, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				// Get two occurences as integers
		        Long quantityOne = (Long) o1[1];
			    Long quantityTwo = (Long) o2[1];
			    // Compare each number of occurences to each other
			    return quantityOne.compareTo(quantityTwo);
			}
		} );
		
		return listOfOccurences;
	}
	
	/**	
	 * 
	 * @param text
	 * @return the differences between each occurence
	 */
	protected Object[][] getDifferencesOfOccurences() {
		Object[][] sortedListOfData = getSortedListOfOccurences();
		
		for (int i = 1; i < sortedListOfData.length; i++) {
			// Add the absolute difference as an int to the sortedListOfData array
			sortedListOfData[i][2] = Math.toIntExact(
				Math.abs(
					(Long)sortedListOfData[i][1] - (Long)sortedListOfData[i - 1][1]
				)
			); 
		}
		
		return sortedListOfData;
	}
	
	/** 
	 * @param matrix
	 * @param colIndex
	 * @return
	 */
	protected static int maxCol(Object [][] matrix, int colIndex) {
	    Integer max = Collections.max(Converters.getColAsInts(matrix, colIndex));

	    return max;
	}
	
	/**
	 * 
	 * @param ciphertext
	 * @return
	 */
	protected static int getHighestDifference(Object[][] listOfDifferences) {
		int highestDifference = maxCol(listOfDifferences, 2);
		
		return highestDifference;
	}
	
	/**
	 * Gets the number of occurences of the least frequent of the most frequent letters
	 * @param ciphertext
	 * @return
	 */
	protected static int getLeastFrequentMostFrequentLetterFrequency(Object[][] listOfDifferences) {
		int highestDifference = getHighestDifference(listOfDifferences);
		for (int i = 1; i < EnglishDeterminer.ALPHABET.size(); i++) {
			if ((int)listOfDifferences[i][2] == highestDifference) {
				
				return Math.toIntExact((long)listOfDifferences[i][1]);
			}
		}
		return 0;
	}
}
