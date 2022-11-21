package frequencyanalysissimulator.business;

public interface Cipher {
    String decrypt();

    CharSequence getKey();

    String encrypt();
}
