package churchencoding;

import java.util.function.Function;

public interface Bool extends Function<Object, Function<Object, Object>> {

	public static final Bool TRUE = t -> f -> t;
	public static final Bool FALSE = t -> f -> f;
	
	default Bool and(Bool other) {
		return (Bool) this.apply(other).apply(Bool.FALSE);
	}
	
	default Bool or(Bool other) {
		return (Bool) this.apply(Bool.TRUE).apply(other);
	}
	
	default Bool not() {
		return (Bool) this.apply(FALSE).apply(TRUE);
	}
	
	default Bool xor(Bool other) {
		return (Bool) this.apply(other.not()).apply(other);
	}
}
