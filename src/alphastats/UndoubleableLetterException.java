package alphastats;

public final class UndoubleableLetterException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4257561531809238144L;

	// Parameterless Constructor
	public UndoubleableLetterException() {
		super("That letter is not doubleable");
	}
}
