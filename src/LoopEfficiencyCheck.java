import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LoopEfficiencyCheck {

	public static final int ITERATIONS = 10;

	 public static void main(String[] args) {
	    List<Character> alphabetList = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmopqrstuvwxyz123456789".chars()
	            .mapToObj(c -> Character.valueOf((char) c)).collect(Collectors.toList());
	    ArrayList<Character> copy = new ArrayList<>(alphabetList);
	    List<Character> unmodifiableAlphabetList = Collections.unmodifiableList(copy);

	    double timeA = checkTime(ITERATIONS, () -> {
	        List<Character> modifiableCollection = new ArrayList<>(alphabetList);
	        modifiableCollection.removeIf(next -> checkRemove(next));
	    });
	    System.out.println("A : " + timeA);

	    double timeB = checkTime(ITERATIONS, () -> {
	        List<Character> modifiableCollection = new ArrayList<>(alphabetList);
	        Iterator<Character> iterator = modifiableCollection.iterator();
	        while (iterator.hasNext()) {
	            if (checkRemove(iterator.next())) {
	                iterator.remove();
	                break;
	            }
	        }
	    });
	    System.out.println("B : " + timeB);

	    double timeC = checkTime(ITERATIONS, () -> {
	        List<Character> modifiableCollection = new ArrayList<>(alphabetList);
	        int size = modifiableCollection.size();
	        for (int i = 0; i < size; i++) {
	            Character character = unmodifiableAlphabetList.get(i);
	            if (checkRemove(character)) {
	                modifiableCollection.remove(i);
	                break;
	            }
	        }
	    });
	    System.out.println("C : " + timeC);

	    double timeD = checkTime(ITERATIONS, () -> {
	        List<Character> modifiableCollection = new ArrayList<>(alphabetList);
	        for (Character c : unmodifiableAlphabetList) {
	            if (checkRemove(c)) {
	                modifiableCollection.remove(c);
	                break;
	            }
	        }
	    });
	    System.out.println("D : " + timeD);
	}

	private static boolean checkRemove(Character next) {
	    return next.equals('W');
	}
	
	private static double checkTime(long count, Runnable fn) {
	    List<Long> diffs = new ArrayList<>();

	    for (int i = 0; i < count; i++) {
	        long now = System.nanoTime();
	        fn.run();
	        long after = System.nanoTime();
	        long nanoDiff = after - now;
	        diffs.add(nanoDiff);
	    }
	    double average = diffs.stream()
	            .mapToLong(l -> l)
	            .average()
	            .orElse(0L);
	    return average;
	}

}
