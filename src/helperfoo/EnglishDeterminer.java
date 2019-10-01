package helperfoo;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.sf.extjwnl.*;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

public final class EnglishDeterminer {
	public static List<Character> ALPHABET = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	public static Character[] CHARS_TO_SKIP = { ' ', '!', '.', '?', ',', ';', '\'', '"', '(', ')', '[', ']', '{', '}', '-', '—', '’'};

	/** Determines if a string is in the extended Java WordNet Library dictionary and has the correct part of speech
	 * @param pos
	 * @param word
	 * @return whether the given string is an English word
	 * @throws JWNLException
	 */
	public static boolean isWord(String word) throws JWNLException {
		Dictionary d = Dictionary.getDefaultResourceInstance();
		
		Collection<POS> POSList = EnumSet.allOf(POS.class);
		
		List<String> reflexivePronouns = Converters.convertStringToListOfStrings("myself yourself herself himself itself ourselves yourselves themselves");
		List<String> outliers = Converters.convertStringToListOfStrings("the this that of these those and you for to with seafloor moviemakers than");
		List<String> personalPronouns = Converters.convertStringToListOfStrings("she we");
		List<String> prepositions = Converters.convertStringToListOfStrings("");
		List<String> conjunctions = Converters.convertStringToListOfStrings("since");
		List<String> combinedOutliers = Stream.of(reflexivePronouns, outliers, personalPronouns, prepositions, conjunctions)
	            .flatMap(x -> x.stream())
	            .collect(Collectors.toList());
		boolean isWord = POSList.stream().anyMatch(c -> {
			try {
				return d.lookupIndexWord(c, word) != null;
			} catch (JWNLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		});
		
		return isWord || combinedOutliers.contains(word);
	}
	
	/**
	 * 
	 * @param words
	 * @return
	 * @throws JWNLException
	 */
	public static boolean isSentence(String ...words) throws JWNLException {
		for (String word : words) {
			System.out.println("isWord or isInteger: " + word + " ? " + (isWord(word) || isInteger(word)));
			if (!isWord(word) && !isInteger(word))
				return false;
		}
		
		return true;
	}
	
	/**
	 * Returns whether or not a given character is a space or a punctuation mark
	 * @param c the character being checked
	 * @return true if a character is either a space or a punctuation
	 * false if the character is anything else
	 */
	public static boolean isSpaceOrPunctuation(Character c) {
		for (char item : CHARS_TO_SKIP) {
			if (c.equals(item)) {
				return true; // No need to look further.
			} 
		}
		
		return false;
	}
	
	/**
	 * Copied from <a href = "https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java">a Stack Overflow question and answer</a>
	 * @param input
	 * @return
	 */
	public static boolean isInteger( String input ) {
	    try {
	        Integer.parseInt( input );
	        return true;
	    }
	    catch(NumberFormatException e ) {
	        return false;
	    }
	}
}
