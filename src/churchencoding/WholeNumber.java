package churchencoding;

public interface WholeNumber extends Tuple<Bool, NaturalNumber> {

	public static final WholeNumber ZERO = makePositiveNumber(NaturalNumber.ZERO);
	
	default Bool isZero() {
		return this.cdr().isZero();
	}
	
	public static WholeNumber makePositiveNumber(NaturalNumber n) {
		return f -> f.apply(Bool.TRUE).apply(n);
	}
	
	public static WholeNumber makeNegativeNumber(NaturalNumber n) {
		return f -> f.apply(Bool.FALSE).apply(n);
	}
	
	
}
