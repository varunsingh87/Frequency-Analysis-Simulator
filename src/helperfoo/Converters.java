package helperfoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains methods for converting types to other types that don't already have
 * one-liner methods
 * 
 * @author Varun S
 *
 */
public final class Converters {

	/**
	 * Converts a string to a list of characters
	 * 
	 * @param value of the string that needs to be converted
	 * @return the converted list
	 */
	public static List<Character> convertStringToListOfCharacters(String value) {
		List<Character> cipherchars = new ArrayList<Character>();
		for (char ch : value.toCharArray()) {
			cipherchars.add(Character.toLowerCase(ch));
		}

		return cipherchars;
	}

	/**
	 * Used for ease when copying lists from the web
	 * 
	 * @param value the set of strings divided by spaces as one string
	 * @return the set of strings as an array list of strings
	 */
	public static List<String> convertStringToListOfStrings(String value) {
		return new ArrayList<String>(Arrays.asList(value.split(" ")));
	}

	/**
	 * Converts a list to string without spaces in between elements
	 * @param list the list of objects of type T
	 * @example if the given list is {a, b, c}, it would return abc	
	 * @return the converted object as a string
	 */
	public static <T> String convertListToString(List<T> list) {
		String s = "";

		for (int i = 0; i < list.size(); i++) {
			s += list.get(i).toString();
		}

		return s;
	}

	public static ArrayList<Integer> getColAsInts(Object[][] matrix, int colIndex) {
		ArrayList<Integer> intCol = new ArrayList<Integer>();
		for (int i = 1; i < matrix.length; i++) {
			Object[] row = matrix[i];
			intCol.add((Integer) row[colIndex]);
		}

		return intCol;
	}

	public static ArrayList<Character> getColAsChars(Object[][] matrix, int colIndex) {
		ArrayList<Character> charCol = new ArrayList<Character>();
		for (Object[] row : matrix) {
			charCol.add((Character) row[colIndex]);
		}

		return charCol;
	}

	public static Object[][] toMultiDimensionalArray(ArrayList<ArrayList<Object>> ec) {
		Object[][] ei = new Object[26][3];
		for (int i = 0; i < ec.size(); i++) {
			List<Object> row = ec.get(i); // Get each row

			// Perform equivalent `toArray` operation
			Object[] copy = new Object[3];
			for (int j = 0; j < row.size(); j++) {
				// Manually loop and set individually
				copy[j] = row.get(j);
			}

			ei[i] = copy;
		}
		return ei;
	}
}
