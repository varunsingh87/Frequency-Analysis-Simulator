package frequencyanalysissimulator.crypto;

/**
 * A model for the algorithms that could be used for no-key Vigenere decryption
 */
public class DecryptionSettings {
	private KeyLengthMethod keyLengthMethodChoice;
	private CaesarDecryptionMethod caesarDecryptionMethodChoice;
	private String cipherType;

	public DecryptionSettings(KeyLengthMethod klm, CaesarDecryptionMethod cdm) {
		keyLengthMethodChoice = klm;
		caesarDecryptionMethodChoice = cdm;
	}

	public KeyLengthMethod getKeyLengthMethod() {
		return keyLengthMethodChoice;
	}

	public void setKeyLengthMethod(KeyLengthMethod klm) {
		keyLengthMethodChoice = klm;
	}

	public CaesarDecryptionMethod getCaesarDecryptionMethodChoice() {
		return caesarDecryptionMethodChoice;
	}

	public void setCaesarDecryptionMethod(CaesarDecryptionMethod cdm) {
		caesarDecryptionMethodChoice = cdm;
	}
}