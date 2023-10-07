package frequencyanalysissimulator.crypto;

/**
 * Algorithms for the first step of Vigenere decryption: Calculation of the length of the key.
 * The enumerated values are listed in descending order of accuracy and reliability.
 */
public enum KeyLengthMethod {
	// KASISKI,
	IOC("Index of Coincidence"), FRIEDMAN("Friedman's Test");

	private final String fullName;

	private KeyLengthMethod(final String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return fullName;
	}
}
