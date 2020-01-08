package secretwriting;

public abstract class Cipher extends Substitution implements Encipherable, Decipherable {

	public abstract String decrypt();
	
	public abstract String encrypt();
	
	public Cipher(String givenText) {
		super(givenText);
	}
}
