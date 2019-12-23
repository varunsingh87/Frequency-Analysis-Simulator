package secretwriting;

public class RailFence extends Transposition {

	public RailFence(String givenText) {
		super(givenText);
	}
	
	public String decrypt() {
		return null;
	}
	
	public String encrypt(long cycles) {
		System.out.println(cycles);
		return null;
	}
	
	public String encrypt() {
		return encrypt(Math.round(Math.random() * 10));
	}
}
