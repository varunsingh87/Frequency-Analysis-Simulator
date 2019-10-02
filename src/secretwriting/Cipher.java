package secretwriting;

public abstract class Cipher extends Substitution implements Encipherable, Decipherable {
	private String text;
	final private int textLength;
	
	public abstract String decrypt();
	
	public abstract String encrypt();
	
	public Cipher(String givenText) {
		setText(givenText);
		textLength = givenText.length();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public int length() {
		return textLength;
	}
}
