package churchencoding;

import java.util.function.Function;

public interface Bool extends Function<Object, Function<Object, Object>> {

	public static final Bool TRUE = t -> f -> t;
	public static final Bool FALSE = t -> f -> f;
	
}
