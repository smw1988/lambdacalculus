package churchencoding;

public class ChurchObjectConverter {

	public static Bool fromBoolean(boolean b) {
		return b ? Bool.TRUE : Bool.FALSE;
	}
	
	public static boolean toBoolean(Bool b) {
		return (boolean) (Boolean) b.apply(Boolean.TRUE).apply(Boolean.FALSE);
	}
	
	public static NaturalNumber fromInt(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("input should be zero or positive.");
		}
		
		return n == 0 ? NaturalNumber.ZERO : fromInt(n - 1).inc();
	}
	
	public static int toInt(NaturalNumber n) {
		return (Integer) (n.apply(x -> ((Integer) x) + 1).apply(0));
	}
}
