package churchencoding;

import java.util.function.Function;

public interface NaturalNumber extends Function<Function<Object, Object>, Function<Object, Object>> {

	public static final NaturalNumber ZERO = f -> x -> x;
	
	default NaturalNumber inc() {
		return f -> x -> f.apply(this.apply(f).apply(x));
	}
	
	/*
	 * dec = n => f => x => n(g => h => h(g(f)))(_ => x)(Id)
	 * 
	 * g: (Object -> Object) -> Object
	 * h: Object -> Object
	 * g(f): Object
	 * h(g(f)): Object
	 * h => h(g(f)): (Object -> Object) -> Object
	 * g => h => h(g(f)): ((Object -> Object) -> Object) -> (Object -> Object) -> Object
	 * n(g => h => h(g(f))): ((Object -> Object) -> Object) -> (Object -> Object) -> Object
	 * _ => x: (Object -> Object) -> Object
	 * n(g => h => h(g(f)))(_ => x): (Object -> Object) -> Object
	 * Id: Object -> Object
	 * n(g => h => h(g(f)))(_ => x)(Id): Object
	 */
	@SuppressWarnings("unchecked")
	default NaturalNumber dec() {
		return f -> x -> 
			((Function<Function<Object, Object>, Object>) this
				.apply(
					g -> (Function<Function<Object, Object>, Object>)
						(Function<Object, Object> h) -> h.apply(
							((Function<Function<Object, Object>, Object>) g).apply(f)))
				.apply((Function<Function<Object, Object>, Object>) __ -> x))
			.apply(FunctionUtility::id);
	}
	
	default Bool isZero() {
		return (Bool) this.apply(__ -> Bool.FALSE).apply(Bool.TRUE);
	}
	
	default NaturalNumber add(NaturalNumber other) {
		return f -> x -> other.apply(f).apply(this.apply(f).apply(x));
	}
}
