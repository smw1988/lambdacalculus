package churchencoding;

import java.util.function.Function;

public interface NaturalNumber extends Function<Function<Object, Object>, Function<Object, Object>> {

	public static final NaturalNumber ZERO = f -> x -> x;
	
	default NaturalNumber inc() {
		return f -> x -> f.apply(this.apply(f).apply(x));
	}
	
	default Bool isZero() {
		return (Bool) this.apply(__ -> Bool.FALSE).apply(Bool.TRUE);
	}
}
