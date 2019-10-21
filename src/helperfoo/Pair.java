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
		if (this == obj) {
			return true;
		}
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
}
