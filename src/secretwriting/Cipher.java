package secretwriting;

public abstract class Cipher extends Substitution implements Encipherable, Decipherable {
	protected String text;
	
	public abstract String decrypt();
	
	public abstract String encrypt();
	
	public Cipher(String givenText) {
		setText(givenText);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
