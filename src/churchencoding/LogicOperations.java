package churchencoding;

public class LogicOperations {

	public static Bool and(Bool b1, Bool b2) {
		return (Bool) b1.apply(b2).apply(Bool.FALSE);
	}
	
}
