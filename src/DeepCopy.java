import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alphastats.AlphabeticalStatistics;

public class DeepCopy {
	public static void main(String[] args) {
		List<Character> modifiableCollection = new ArrayList<Character>(Arrays.asList(AlphabeticalStatistics.ALL_LETTERS));
		ArrayList<Character> compareCollection = new ArrayList<Character>();
		compareCollection.addAll(modifiableCollection);
		modifiableCollection.remove(0);
		System.out.println(compareCollection);
		System.out.println(modifiableCollection);
	}
}
