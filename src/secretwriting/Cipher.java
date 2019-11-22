package secretwriting;

import java.util.ArrayList;

import alphastats.AlphabeticalStatistics;

public abstract class Cipher extends Substitution implements Encipherable, Decipherable {

	public abstract String decrypt();
	
	public abstract String encrypt();
	
	public Cipher(String givenText) {
		super(givenText);
	}
	
	public static String[] getWordsWithOneLowerCase(String text) {
		ArrayList<String> words = new ArrayList<String>();
		for (String element : text.split(" ")) {
			if(AlphabeticalStatistics.needsOneLetter(element)) {
				words.add(element);
			}
		}
		return words.toArray(new String[0]);
	}
}
