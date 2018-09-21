package churchencoding;

public interface WholeNumber extends Tuple<Bool, NaturalNumber> {

	public static final WholeNumber ZERO = makePositiveNumber(NaturalNumber.ZERO);
	
	default Bool isZero() {
		return this.cdr().isZero();
	}
	
	public static WholeNumber makePositiveNumber(NaturalNumber n) {
		return makeWholeNumberImpl(Bool.TRUE, n);
	}
	
	public static WholeNumber makeNegativeNumber(NaturalNumber n) {
		return makeWholeNumberImpl(Bool.FALSE, n);
	}
	
	static WholeNumber makeWholeNumberImpl(Bool sign, NaturalNumber amplitude) {
		return f -> f.apply(sign).apply(amplitude);
	}
	
	static WholeNumber makeWholeNumber(Bool sign, NaturalNumber amplitude) {
		return ComputeFlow.ifThenElse(amplitude.isZero(), 
				__ -> makeWholeNumberImpl(Bool.TRUE, NaturalNumber.ZERO),
				__ -> makeWholeNumberImpl(sign, amplitude));
	}
	
	default WholeNumber add(WholeNumber other) {
		return ComputeFlow.ifThenElse(this.car().eq(other.car()), 
				__ -> makeWholeNumberImpl(this.car(), this.cdr().add(other.cdr())), 
				__ -> ComputeFlow.ifThenElse(this.cdr().ge(other.cdr()), 
						___ -> makeWholeNumberImpl(this.car(), this.cdr().subtract(other.cdr())), 
						___ -> makeWholeNumberImpl(other.car(), other.cdr().subtract(this.cdr()))));
	}
	
}
