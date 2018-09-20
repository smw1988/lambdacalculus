package churchencoding;

import java.util.function.Function;

public interface Tuple<T1, T2> extends Function<Bool, Object> {

	public static <T1, T2> Tuple<T1, T2> cons(T1 t1, T2 t2) {
		return f -> f.apply(t1).apply(t2);
	}
	
	@SuppressWarnings("unchecked")
	default T1 car() {
		return (T1) this.apply(Bool.TRUE);
	}
	
	@SuppressWarnings("unchecked")
	default T2 cdr() {
		return (T2) this.apply(Bool.FALSE);
	}
}
