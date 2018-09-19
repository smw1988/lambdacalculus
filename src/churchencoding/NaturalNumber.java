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
	
	default NaturalNumber subtract(NaturalNumber other) {
		return (NaturalNumber) other.apply(n -> ((NaturalNumber) n).dec()).apply(this);
	}
	
	default NaturalNumber multiply(NaturalNumber other) {
		return (NaturalNumber) other.apply(n -> ((NaturalNumber) n).add(this)).apply(ZERO);
	}
	
	default NaturalNumber exp(NaturalNumber exponent) {
		return (NaturalNumber) exponent.apply(n -> ((NaturalNumber) n).multiply(this)).apply(ZERO.inc());
	}
	
	default Bool le(NaturalNumber other) {
		return this.subtract(other).isZero();
	}
	
	default Bool ge(NaturalNumber other) {
		return other.subtract(this).isZero();
	}
	
	default Bool eq(NaturalNumber other) {
		return this.le(other).and(this.ge(other));
	}
	
	default Bool gt(NaturalNumber other) {
		return this.le(other).not();
	}
	
	default Bool lt(NaturalNumber other) {
		return this.ge(other).not();
	}
	
	default Bool ne(NaturalNumber other) {
		return this.eq(other).not();
	}
}
