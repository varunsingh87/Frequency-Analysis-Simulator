package frequencyanalysissimulator.crypto;

import java.util.Locale;

public enum CaesarDecryptionMethod {
	KERCKHOFF, KASISKI;

	@Override
	public String toString() {
		return this.name().substring(0, 1).toUpperCase(Locale.ROOT) + this.name().substring(1).toLowerCase(Locale.ROOT);
	}
}