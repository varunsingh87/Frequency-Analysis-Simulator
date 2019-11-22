package secretwriting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import alphastats.AlphabeticalStatistics;
import alphastats.Frequencies;
import helperfoo.Converters;
import helperfoo.EnglishDeterminer;
import helperfoo.Pair;
import net.sf.extjwnl.JWNLException;

public class MonoalphabeticCipher extends Cipher {
	List<String> solvedLetters = new ArrayList<String>();
	List<String> replacedLetters = new ArrayList<String>();
	
	public MonoalphabeticCipher(String givenText) {
		super(givenText);
	}

	/**
	 * Creates a cipher alphabet
	 * 
	 * @return the cipher alphabet - the shuffled plaintext alphabet
	 */
	public ArrayList<Character> generateCipherAlphabet() {
		ArrayList<Character> cipheralphabet = new ArrayList<Character>();
		for (char letter : EnglishDeterminer.ALPHABET) {
			cipheralphabet.add(letter);
		}

		Collections.shuffle(cipheralphabet);

		return cipheralphabet;
	}

	/**
	 * @param ciphertext the cipher that is deciphered
	 * @return the completely deciphered or almost completely deciphered
	 *         monoalphabetic substitution cipher in plaintext
	 */
	public String decrypt() {
		// Object[][] listOfDifferences = getDifferencesOfOccurences(getText());
		// Object[][] mostFrequentLetters = getMostFrequentLetters(getText(),
		// listOfDifferences);
		Frequencies f = new Frequencies(this); // Defines a new Frequencies object
		
		System.out.println(getText());
		
		Pair mostFrequentFinalLetter = f.getMostFrequentFinalLetter();
		//Pair vowel = f.getMostSocialLetter();
		char[] threeLetterWord = f.getMostFrequentTrigraph();
		char[] twoLetterWord = f.getMostFrequentDigraph();
		Pair mostFrequentInitialLetter = f.getMostFrequentInitialLetter();
		Object[][] mostFrequentLetters = f.getMostFrequentLetters();
		
		//setText(f.replaceBigrams());
		


		//replaceLetters(vowel.props.toUpperCase(), "a");
		
		// Trigrams
		String notSolvedTrigram = "   ";
		for (String trigram : AlphabeticalStatistics.TRIGRAPHS) {
			if (!solvedLetters.contains(trigram)) {
				notSolvedTrigram = trigram;
				System.out.println(trigram);
				break;
			}
		}
		
		if (!notSolvedTrigram.equals("   ")) {
			for (int i = 0; i <= 2; i++) {
				char solvedLetter = notSolvedTrigram.charAt(i);
				char replacedLetter = threeLetterWord[i];
				
				setText(getText().replace(replacedLetter, solvedLetter));	
			
			
				solvedLetters.add(Character.toString(solvedLetter));
				replacedLetters.add(Character.toString(replacedLetter));
			}
			System.out.println("The common trigraph " + String.valueOf(threeLetterWord) + " in the ciphertext was replaced with " + notSolvedTrigram);
		}
		
		// Digraphs
		String notSolvedDigraph = "  ";
		for (String bigram : AlphabeticalStatistics.DIGRAPHS) {
			if (!solvedLetters.contains(Character.toString(bigram.charAt(0))) && !solvedLetters.contains(Character.toString(bigram.charAt(1)))) {
				notSolvedDigraph = bigram;
				System.out.println(bigram);
				break;
			}
		}
		
		if (notSolvedDigraph != "  ") {
			for (int i = 0; i <= 1; i++) {
				setText(getText().replace(twoLetterWord[i], notSolvedDigraph.charAt(i)));
			}
			
			System.out.println("The common digraph " + String.valueOf(twoLetterWord) + " in the ciphertext was replaced with " + notSolvedDigraph);
			
			for (char letter : notSolvedDigraph.toCharArray()) {
				solvedLetters.add(Character.toString(letter)); 
			}
		}
		
		// Initial letters
		char notSolvedInitialLetter = ' ';
		for (char initialLetter : AlphabeticalStatistics.INITIAL_LETTERS) {
			if (!solvedLetters.contains(Character.toString(initialLetter))) {
				notSolvedInitialLetter = Character.toLowerCase(initialLetter);
			}
		}
		
		if (notSolvedInitialLetter != ' ') {
			replaceLetters(mostFrequentInitialLetter.props, Character.toString(notSolvedInitialLetter));
		}
		
		// Final letters
		char notSolvedFinalLetter = ' ';
		for (char finalLetter : AlphabeticalStatistics.FINAL_LETTERS) {
			if (!solvedLetters.contains(Character.toString(finalLetter))) {
				notSolvedFinalLetter = Character.toLowerCase(finalLetter);
			}
		}
		
		if (notSolvedFinalLetter != ' ') {
			replaceLetters(mostFrequentFinalLetter.props, Character.toString(notSolvedFinalLetter));
		}
		
		// All letters
		for (Object[] fl : mostFrequentLetters) {
			for (char l : AlphabeticalStatistics.ALL_LETTERS) {
				for (String m : solvedLetters) {
					if (m.equals(Character.toString(l)) && !solvedLetters.contains(Character.toString(l))) {
						replaceLetters(fl[0].toString(), Character.toString(l));
						break;
					}
				}	
			}
			
		}
		
		// Random
		//testRandom();
		
		System.out.println(replacedLetters.toString());
		System.out.println(solvedLetters.toString());
		
		return getText();
	}

	/**
	 * Encrypts a message into a monoalphabetic cipher
	 * 
	 * @param plaintext
	 * @return
	 */
	public String encrypt() {
		List<Character> plaintextchars = Converters.convertStringToListOfCharacters(getText());
		List<Character> cipherAlphabet = generateCipherAlphabet();
		List<Character> plainAlphabet = EnglishDeterminer.ALPHABET;

		List<Character> cipherchars = plaintextchars.stream().map(p -> {
			if (EnglishDeterminer.isSpaceOrPunctuation(p) || EnglishDeterminer.isInteger(p.toString())) {
				return p;
			}

			int nthLetter = plainAlphabet.indexOf(Character.toLowerCase(p));

			return cipherAlphabet.get(nthLetter);
		}).collect(Collectors.toList());

		return Converters.convertListToString(cipherchars);
	}
	
	private void replaceLetters(String toReplace, String replacement) {
		replaceLetters(toReplace, replacement, replacement, toReplace);
	}
	
	@SuppressWarnings("unused")
	private void replaceLetters(String toReplace, String replacement, String toAdd) {
		replaceLetters(toReplace, replacement, toAdd, toReplace);
	}
	
	private void replaceLetters(String toReplace, String replacement, String toAdd, String toAdd2) {
		setText(getText().replace(toReplace, replacement));
		solvedLetters.add(toAdd);
		replacedLetters.add(toAdd2);
	}

	public String magic() {
		return null;
	}

	public void printCipherAlphabetAsTable() {

	}
	
	public void testRandom() {
		char randomLetter = EnglishDeterminer.ALPHABET.get((int) (Math.round(Math.random() * 25)));
		while (solvedLetters.contains(Character.toString(randomLetter))) {
			randomLetter = EnglishDeterminer.ALPHABET.get((int) (Math.round(Math.random() * 25)));
		}
		
		char secondRandomLetter = EnglishDeterminer.ALPHABET.get((int) (Math.round(Math.random() * 25)));
		while (replacedLetters.contains(Character.toString(secondRandomLetter))) {
			secondRandomLetter = EnglishDeterminer.ALPHABET.get((int) (Math.round(Math.random() * 25)));
		}
		String potentialText = getText().replace(Character.toUpperCase(secondRandomLetter), randomLetter);
		try {
			System.out.println(potentialText);
			System.out.println(Character.toUpperCase(secondRandomLetter));
			System.out.println(randomLetter);
			if(EnglishDeterminer.isSentence(Cipher.getWordsWithOneLowerCase(potentialText))) {
				replaceLetters(Character.toString(Character.toUpperCase(secondRandomLetter)), Character.toString(randomLetter));
			}
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
}
