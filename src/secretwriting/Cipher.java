package secretwriting;

public abstract class Cipher extends SecretWriting {
	public abstract String decrypt();
	
	public abstract String encrypt();
	
	public abstract String magic();
}
