package secretwriting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import helperfoo.Converters;
import helperfoo.EnglishDeterminer;
import net.sf.extjwnl.JWNLException;

public class CaesarShiftCipher extends Cipher {
	
	public CaesarShiftCipher(String givenText) {
		super(givenText);
	}
	
	/**
	 * 
	 * @param ciphertext
	 * @param key
	 * @return the plaintext
	 * @throws JWNLException 
	 */
	public String decrypt() {
	
		for (int i = 1; i <= 26; i++) {
			final int key = i;
			String shiftedText = this.shiftLetters(key);
		    try {    	
		    	String[] splitText = shiftedText.split(" |\\.|—|,");
		    	System.out.println(Arrays.toString(splitText));
				boolean isSentence = EnglishDeterminer.isSentence(splitText);
		    	if (isSentence) {
					return shiftedText;
				}
		    } catch (PatternSyntaxException e) {
		    	System.out.println(e.getDescription());
		    } catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "This ciphertext has non-English plaintext";
	}
	
	/** Generates a random integer key between 1 and 26 (inclusive) and shifts all letters in the given plain text by this key
	 * @return the Caesar-shift-cipher-encrypted cipher text
	 */
	public String encrypt() {
		String ciphertext = this.shiftLetters((int)(Math.random() * 25));
		return ciphertext;
		
	}
	
	/**
	 * Automatically decrypts the testing cipher so the beta tester does not have to go through the redundant (for him/her) process of inputting 
	 */
	public static String magic() {
		return new CaesarShiftCipher("OF MIT 34 BTAKL LOFET MIT K.D.L. MOMAFOE CAL ROLEGXTKTR GF MIT LTAYSGGK LGWMI GY FTCYGWFRSAFR, OM IAL ZTEGDT MIT CGKSR'L DGLM YADGWL LIOHCKTEQ -- A KWLMOFU IWSQ ALLAOSTR ZB IWFRKTRL GY TVHSGKTKL AFR DGXOTDAQTKL, LASXGKL AFR MGWKOLML, LEOTFMOLML AFR YTRTKAS CAMEIRGUL. ASS AUKTT MIAM MIT GFET-UKAFR LIOH OL KAHORSB YASSOFU AHAKM. KTLMOFU GF MIT OEB FGKMI AMSAFMOE LTAZTR DGKT MIAF MCG DOSTL RGCF, WHKOUIM ZWM LHSOM OF MCG, MIT YKAUOST DALL OL LSGCSB LWEEWDZOFU MG KWLM, EGKKGLOXT LASML, DOEKGZTL AFR EGSGFOTL GY RTTH-LTA EKTAMWKTL.").decrypt();
	}
	
	/**
	 * @author Varun Singh
	 * @param key the number of letters to shift to the right from the original letter
	 * @param unshiftedText the ciphertext or the plaintext
	 * @return the shifted output message
	 */
	protected String shiftLetters(int key) {

		List<Character> unshiftedChars = Converters.convertStringToListOfCharacters(getText());
		
	    List<Character> shiftedText = unshiftedChars
	      .stream()
	      .map( c -> {
	    	  
	    	  if (EnglishDeterminer.isSpaceOrPunctuation(c) || EnglishDeterminer.isInteger(c.toString())) {
	    		  return c;
	    	  }
	
	    	  // Shift the letter by an integer, key
	    	  int index = EnglishDeterminer.ALPHABET.indexOf(Character.toLowerCase(c));
	    	  int newPosition = index + key % 26;
	    	  if (newPosition > 25) {
	    		  newPosition -= 26; 
	    	  }
	    	  return EnglishDeterminer.ALPHABET.get(newPosition); // Return the new position
	      }).collect(Collectors.toList());

	      return Converters.convertListToString(shiftedText);
	}

	public ArrayList<Character> generateCipherAlphabet() {
		
		return null;
	}

	public void printCipherAlphabetAsTable() {
		
		System.out.println(generateCipherAlphabet());
	}
}
