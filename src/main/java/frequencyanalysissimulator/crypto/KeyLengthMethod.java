package frequencyanalysissimulator.crypto;

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
