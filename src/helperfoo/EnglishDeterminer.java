package helperfoo;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.sf.extjwnl.*;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

public final class EnglishDeterminer {
	/** Determines if a string is in the extended Java WordNet Library dictionary and has the correct part of speech
	 * @param pos
	 * @param word
	 * @return whether the given string is an English word
	 * @throws JWNLException
	 */
	public static boolean isWord(String word) throws JWNLException {
		// DONE Implement isWord(String word) method
		Dictionary d = Dictionary.getDefaultResourceInstance();
		
		Collection<POS> POSList = EnumSet.allOf(POS.class);
		
		List<String> reflexivepronouns = Converters.convertStringToListOfStrings("myself yourself herself himself itself ourselves yourselves themselves");
		List<String> outliers = Converters.convertStringToListOfStrings("the this that of these those and you for");
		List<String> combinedOutliers = Stream.of(reflexivepronouns, outliers)
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
	public static boolean isSentence(String[] words) throws JWNLException {
		for (String word : words) {
			if (!isWord(word))
				return false;
		}
		
		return true;
	}
}
