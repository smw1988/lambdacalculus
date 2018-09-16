package churchencoding;

public class ChurchObjectConverter {

	public static Bool fromBoolean(boolean b) {
		return b ? Bool.TRUE : Bool.FALSE;
	}
	
	public static boolean toBoolean(Bool b) {
		return (boolean) (Boolean) b.apply(Boolean.TRUE).apply(Boolean.FALSE);
	}
}
