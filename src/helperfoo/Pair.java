package helperfoo;

import java.util.Objects;

/**
 * An object that contanis a property and a value, or two values
 * The first parameter MUST be a String OR a char. 
 * The second parameter can be a String, int, char, Pair, etc.
 */
public class Pair {
	public String props;
	public Object val;
	
	public Pair(String property, Object value) {
		props = property;
		val = value;
	}
	
	public Pair(char property, Object value) {
		props = String.valueOf(property);
		val = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair other = (Pair) obj;
		return Objects.equals(props, other.props) && Objects.equals(val, other.val);
	}
	
	public String toString() {
		return props + ": " + val;
	} 
	
	@Override
	public int hashCode() {
		return Objects.hash(props, val);
	}
	
	public int compareToLong(Pair pair2) {
		Pair pair1 = this;
		if ((long)pair1.val > (long)pair2.val) {
			return 1;
		} else if ((long)pair1.val < (long)pair2.val) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public int compareToInt(Pair pair2) {
		Pair pair1 = this;
		if ((int)pair1.val > (int)pair2.val) {
			return 1;
		} else if ((int)pair1.val < (int)pair2.val) {
			return -1;
		} else {
			return 0;
		}
	}
}
