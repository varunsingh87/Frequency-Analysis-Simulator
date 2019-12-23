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

public class FrequencyHelpers {
	private String text;
	private String getText() {
		return text;
	}
	
	public FrequencyHelpers(String givenText) {
		text = givenText;
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
