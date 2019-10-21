package helperfoo;

/**
 * Set of static methods for printing objects (mainly arrays) that 
 * don't have default printing methods built-in;
 * it is also for developing purposes when copying and pasting something that
 * must turn into a list (temporary invokation)
 *
 */
public class Printers {
	public static void printStringArray(String str) {
		System.out.print("{");
		for (String element : str.split(" ")) {
			System.out.print("\"" + element + "\", ");
		}
		System.out.println("}");
	}

	public static void printStringArray(String str, String regex) {
		String[] asArr = str.split(regex);
		System.out.print("{");
		for (String element : asArr) {
			System.out.print("\"" + element + "\", ");
		}
		System.out.println("}");
	}
	
	public static void printCharArray(String str) {
		System.out.print("{");
		for (String element : str.split(" ")) {
			System.out.print("'" + element + "', ");
		}
		System.out.println("}");
	}
	
	public static void printCharArray(String str, String regex) {
		System.out.print("{");
		for (String element : str.split(" ")) {
			System.out.print("'" + element + "', ");
		}
		System.out.println("}");
	}
}
