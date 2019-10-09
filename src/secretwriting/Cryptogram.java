package secretwriting;

public class Cryptogram extends SecretWriting {
	private String cryptoText;
	final private int textLength;
	
	public Cryptogram(String givenText) {
		setText(givenText);
		textLength = givenText.length();
	}
	
	public String getText() {
		return cryptoText;
	}

	public void setText(String text) {
		this.cryptoText = text;
	}
	
	public int length() {
		return textLength;
	}
	
	public String[] getWords() {
		return cryptoText.split(" ");
	}
	
	public static String define() {
		return "A cryptogram is a puzzle consisting of encrypted or scrambled text";
	}
}
