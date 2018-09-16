package churchencoding;

public class LogicOperations {

	public static Bool and(Bool b1, Bool b2) {
		return b1.and(b2);
	}
	
	public static Bool or(Bool b1, Bool b2) {
		return b1.or(b2);
	}
	
	public static Bool not(Bool b) {
		return b.not();
	}
	
	public static Bool xor(Bool b1, Bool b2) {
		return b1.xor(b2);
	}
	
}
