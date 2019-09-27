package secretwriting;

public abstract class Cipher extends SecretWriting implements Encipherable {
	private String text;
	
	public abstract String decrypt();
	
	public abstract String encrypt();
	
	public abstract String magic();
	
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
