package alphastats;

/**
 * Thrown when <pre>AlphabeticalStatistics.hasDoubleLetter(text, letter)</pre> from the <pre>alphastats</pre> package is called and the second argument, letter, has a value of a character that is not a common bigram
 * @serial -4257561531809238144L
*/
public final class UndoubleableLetterException extends Exception {
	
	private static final long serialVersionUID = -4257561531809238144L;

	// Parameterless Constructor
	public UndoubleableLetterException() {
		super("That letter is not doubleable");
	}
}
