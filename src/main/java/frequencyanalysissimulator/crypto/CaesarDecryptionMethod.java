package frequencyanalysissimulator.crypto;

import java.util.Locale;


/**
 * Possible algorithms for second step of Vigenere decryption - decrypting each coset.
 * Each coset is a Caesar cipher.
 * The enumerated values are listed in descending order of accuracy and reliability.
 */
public enum CaesarDecryptionMethod {
	KERCKHOFF, KASISKI, ALL;

	@Override
	public String toString() {
		return this.name().substring(0, 1).toUpperCase(Locale.ROOT) + this.name().substring(1).toLowerCase(Locale.ROOT);
	}
}