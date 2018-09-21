package lambda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import churchencoding.ChurchObjectConverter;
import churchencoding.ComputeFlow;
import churchencoding.WholeNumber;

class WholeNumberTest {

	@Test
	void testConversion() {
		assertIntValue(0, WholeNumber.ZERO);
		assertIntValue(1, fromSignedInt(1));
		assertIntValue(2, fromSignedInt(2));
		assertIntValue(3, fromSignedInt(3));
		assertIntValue(-1, fromSignedInt(-1));
		assertIntValue(-2, fromSignedInt(-2));
		assertIntValue(-3, fromSignedInt(-3));
	}
	
	@Test
	void testIsZero() {
		ComputeFlow.ifThenElse(
				WholeNumber.ZERO.isZero(), 
				__ -> null, 
				__ -> { Assertions.fail("ZERO should be zero."); return null; });
		
		ComputeFlow.ifThenElse(
				fromSignedInt(1).isZero(), 
				__ -> { Assertions.fail("ONE should not be zero."); return null; }, 
				__ -> null);
		
		ComputeFlow.ifThenElse(
				fromSignedInt(-1).isZero(), 
				__ -> { Assertions.fail("ONE should not be zero."); return null; }, 
				__ -> null);
	}
	
	@Test
	void testNegate() {
		assertIntValue(0, WholeNumber.ZERO.negate());
		assertIntValue(-2, fromSignedInt(2).negate());
		assertIntValue(3, fromSignedInt(-3).negate());
	}
	
	@Test
	void testAddition() {
		assertIntValue(0, WholeNumber.ZERO.add(WholeNumber.ZERO));
		assertIntValue(2, WholeNumber.ZERO.add(fromSignedInt(2)));
		assertIntValue(2, fromSignedInt(2).add(WholeNumber.ZERO));
		assertIntValue(-1, WholeNumber.ZERO.add(fromSignedInt(-1)));
		assertIntValue(-1, fromSignedInt(-1).add(WholeNumber.ZERO));
		assertIntValue(5, fromSignedInt(2).add(fromSignedInt(3)));
		assertIntValue(-5, fromSignedInt(-3).add(fromSignedInt(-2)));
		assertIntValue(1, fromSignedInt(3).add(fromSignedInt(-2)));
		assertIntValue(-1, fromSignedInt(2).add(fromSignedInt(-3)));
		assertIntValue(1, fromSignedInt(-2).add(fromSignedInt(3)));
		assertIntValue(-1, fromSignedInt(-3).add(fromSignedInt(2)));
		assertIntValue(0, fromSignedInt(2).add(fromSignedInt(-2)));
	}
	
	@Test
	void testSubtraction() {
		assertIntValue(0, WholeNumber.ZERO.subtract(WholeNumber.ZERO));
		assertIntValue(-2, WholeNumber.ZERO.subtract(fromSignedInt(2)));
		assertIntValue(2, fromSignedInt(2).subtract(WholeNumber.ZERO));
		assertIntValue(1, WholeNumber.ZERO.subtract(fromSignedInt(-1)));
		assertIntValue(-1, fromSignedInt(-1).subtract(WholeNumber.ZERO));
		assertIntValue(-1, fromSignedInt(2).subtract(fromSignedInt(3)));
		assertIntValue(-1, fromSignedInt(-3).subtract(fromSignedInt(-2)));
		assertIntValue(5, fromSignedInt(3).subtract(fromSignedInt(-2)));
		assertIntValue(5, fromSignedInt(2).subtract(fromSignedInt(-3)));
		assertIntValue(-5, fromSignedInt(-2).subtract(fromSignedInt(3)));
		assertIntValue(-5, fromSignedInt(-3).subtract(fromSignedInt(2)));
		assertIntValue(0, fromSignedInt(2).subtract(fromSignedInt(2)));
	}
	
	@Test
	void testMultiplication() {
		assertIntValue(0, WholeNumber.ZERO.multiply(WholeNumber.ZERO));
		assertIntValue(0, WholeNumber.ZERO.multiply(fromSignedInt(2)));
		assertIntValue(0, fromSignedInt(2).multiply(WholeNumber.ZERO));
		assertIntValue(0, WholeNumber.ZERO.multiply(fromSignedInt(-1)));
		assertIntValue(0, fromSignedInt(-1).multiply(WholeNumber.ZERO));
		assertIntValue(6, fromSignedInt(2).multiply(fromSignedInt(3)));
		assertIntValue(6, fromSignedInt(-3).multiply(fromSignedInt(-2)));
		assertIntValue(-6, fromSignedInt(3).multiply(fromSignedInt(-2)));
		assertIntValue(-6, fromSignedInt(2).multiply(fromSignedInt(-3)));
	}
	
	@Test
	void testDivision() {
		Assertions.assertThrows(ArithmeticException.class, () -> WholeNumber.ZERO.divide(WholeNumber.ZERO));
		assertIntValue(0, WholeNumber.ZERO.divide(fromSignedInt(2)));
		Assertions.assertThrows(ArithmeticException.class, () -> fromSignedInt(2).divide(WholeNumber.ZERO));
		assertIntValue(0, WholeNumber.ZERO.divide(fromSignedInt(-1)));
		Assertions.assertThrows(ArithmeticException.class, () -> fromSignedInt(-1).divide(WholeNumber.ZERO));
		assertIntValue(2, fromSignedInt(5).divide(fromSignedInt(2)));
		assertIntValue(2, fromSignedInt(-7).divide(fromSignedInt(-3)));
		assertIntValue(-1, fromSignedInt(3).divide(fromSignedInt(-2)));
		assertIntValue(-2, fromSignedInt(6).divide(fromSignedInt(-3)));
		assertIntValue(0, fromSignedInt(2).divide(fromSignedInt(-3)));
	}
	
	private static WholeNumber fromSignedInt(int n) {
		return ChurchObjectConverter.fromSignedInt(n);
	}

	static void assertIntValue(int expected, WholeNumber n) {
		Assertions.assertEquals(expected, ChurchObjectConverter.toSignedInt(n));
	}
	
}
