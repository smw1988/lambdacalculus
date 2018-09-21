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
	
	default WholeNumber negate() {
		return makeWholeNumber(this.car().not(), this.cdr());
	}
	
	default WholeNumber add(WholeNumber other) {
		return ComputeFlow.ifThenElse(this.car().eq(other.car()), 
				__ -> makeWholeNumber(this.car(), this.cdr().add(other.cdr())), 
				__ -> ComputeFlow.ifThenElse(this.cdr().ge(other.cdr()), 
						___ -> makeWholeNumber(this.car(), this.cdr().subtract(other.cdr())), 
						___ -> makeWholeNumber(other.car(), other.cdr().subtract(this.cdr()))));
	}
	
	default WholeNumber subtract(WholeNumber other) {
		return this.add(other.negate());
	}
	
	default WholeNumber multiply(WholeNumber other) {
		return makeWholeNumber(this.car().eq(other.car()), this.cdr().multiply(other.cdr()));
	}
	
	default WholeNumber divide(WholeNumber other) {
		return makeWholeNumber(this.car().eq(other.car()), this.cdr().divide(other.cdr()));
	}
	
}
