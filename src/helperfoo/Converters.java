package helperfoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains methods for converting types to other types that don't already have one-liner methods
 * @author Varun S
 *
 */
public final class Converters {
	
	/** Converts a string to a list of characters
	 * @param value of the string that needs to be converted
	 * @return the converted list
	 */
	public static List<Character> convertStringToListOfCharacters(String value) {
		List<Character> cipherchars = new ArrayList<Character>();
		for (char ch: value.toCharArray()) {
			cipherchars.add(Character.toLowerCase(ch));
		}
		
		return cipherchars;
	}
	
	/**
	 * Used for ease when copying lists from the web
	 * @param value the set of strings divided by spaces as one string
	 * @return the set of strings as an array list of strings
	 */
	public static List<String> convertStringToListOfStrings(String value) {
		return new ArrayList<String>(Arrays.asList(value.split(" ")));
	}
	
	/**
	 * @param list the list of objects of type T
	 * @return the converted string
	 */
	public static <T> String convertListToString(List<T> list) {    
		String s = "";

		for (int i = 0; i < list.size(); i++) {
		    s += list.get(i).toString();
		}

		return s;
	}
	
	public static void printArray(String[] arr) {
		System.out.print("{");
        for (String element: arr) {
            System.out.print("\"" + element + "\", ");
        }
        System.out.print("}\n");
	}
}
