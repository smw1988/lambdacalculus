package lambda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import churchencoding.ChurchObjectConverter;
import churchencoding.ComputeFlow;
import churchencoding.NaturalNumber;

class NaturalNumberTest {

	@Test
	void testConversion() {
		assertIntValue(0, NaturalNumber.ZERO);
		assertIntValue(1, ChurchObjectConverter.fromInt(1));
		assertIntValue(2, ChurchObjectConverter.fromInt(2));
		assertIntValue(3, ChurchObjectConverter.fromInt(3));
	}
	
	@Test
	void testIncrement() {
		assertIntValue(1, NaturalNumber.ZERO.inc());
		assertIntValue(2, NaturalNumber.ZERO.inc().inc());
		assertIntValue(3, NaturalNumber.ZERO.inc().inc().inc());
	}
	
	@Test
	void testDecrement() {
		assertIntValue(0, NaturalNumber.ZERO);
		assertIntValue(0, ChurchObjectConverter.fromInt(1).dec());
		assertIntValue(1, ChurchObjectConverter.fromInt(2).dec());
	}
	
	@Test
	void testIsZero() {
		ComputeFlow.ifThenElse(
				NaturalNumber.ZERO.isZero(), 
				__ -> null, 
				__ -> { Assertions.fail("ZERO should be zero."); return null; });
		
		ComputeFlow.ifThenElse(
				NaturalNumber.ZERO.inc().isZero(), 
				__ -> { Assertions.fail("ONE should not be zero."); return null; }, 
				__ -> null);
	}
	
	@Test
	void testAddition() {
		assertIntValue(3, ChurchObjectConverter.fromInt(1).add(ChurchObjectConverter.fromInt(2)));
		assertIntValue(2, NaturalNumber.ZERO.add(ChurchObjectConverter.fromInt(2)));
		assertIntValue(2, ChurchObjectConverter.fromInt(2).add(NaturalNumber.ZERO));
	}
	
	@Test
	void testSubtraction() {
		assertIntValue(2, ChurchObjectConverter.fromInt(3).subtract(ChurchObjectConverter.fromInt(1)));
		assertIntValue(0, ChurchObjectConverter.fromInt(1).subtract(ChurchObjectConverter.fromInt(3)));
		assertIntValue(0, ChurchObjectConverter.fromInt(2).subtract(ChurchObjectConverter.fromInt(2)));
		assertIntValue(2, ChurchObjectConverter.fromInt(2).subtract(NaturalNumber.ZERO));
		assertIntValue(0, NaturalNumber.ZERO.subtract(ChurchObjectConverter.fromInt(2)));
		assertIntValue(0, NaturalNumber.ZERO.subtract(NaturalNumber.ZERO));
	}
	
	@Test
	void testMultiplication() {
		assertIntValue(1, ChurchObjectConverter.fromInt(1).multiply(ChurchObjectConverter.fromInt(1)));
		assertIntValue(3, ChurchObjectConverter.fromInt(1).multiply(ChurchObjectConverter.fromInt(3)));
		assertIntValue(3, ChurchObjectConverter.fromInt(3).multiply(ChurchObjectConverter.fromInt(1)));
		assertIntValue(6, ChurchObjectConverter.fromInt(2).multiply(ChurchObjectConverter.fromInt(3)));
		assertIntValue(0, NaturalNumber.ZERO.multiply(ChurchObjectConverter.fromInt(2)));
		assertIntValue(0, ChurchObjectConverter.fromInt(2).multiply(NaturalNumber.ZERO));
		assertIntValue(0, NaturalNumber.ZERO.multiply(NaturalNumber.ZERO));
	}
	
	@Test
	void testPower() {
		assertIntValue(1, ChurchObjectConverter.fromInt(2).exp(NaturalNumber.ZERO));
		assertIntValue(2, ChurchObjectConverter.fromInt(2).exp(ChurchObjectConverter.fromInt(1)));
		assertIntValue(8, ChurchObjectConverter.fromInt(2).exp(ChurchObjectConverter.fromInt(3)));
		assertIntValue(1, ChurchObjectConverter.fromInt(1).exp(ChurchObjectConverter.fromInt(1)));
		assertIntValue(1, ChurchObjectConverter.fromInt(1).exp(ChurchObjectConverter.fromInt(3)));
		assertIntValue(0, NaturalNumber.ZERO.exp(ChurchObjectConverter.fromInt(1)));
		assertIntValue(0, NaturalNumber.ZERO.exp(ChurchObjectConverter.fromInt(3)));
		assertIntValue(1, NaturalNumber.ZERO.exp(NaturalNumber.ZERO));
	}
	
	@Test
	void testLessThanOrEqual() {
		BoolTest.testBoolValue(true, NaturalNumber.ZERO.le(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(true, NaturalNumber.ZERO.le(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(true, NaturalNumber.ZERO.le(NaturalNumber.ZERO));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(2).le(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(2).le(ChurchObjectConverter.fromInt(3)));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(2).le(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(1).le(NaturalNumber.ZERO));
	}
	
	@Test
	void testGreaterThanOrEqual() {
		BoolTest.testBoolValue(false, NaturalNumber.ZERO.ge(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(false, NaturalNumber.ZERO.ge(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(true, NaturalNumber.ZERO.ge(NaturalNumber.ZERO));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(2).ge(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(2).ge(ChurchObjectConverter.fromInt(3)));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(2).ge(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(1).ge(NaturalNumber.ZERO));
	}
	
	@Test
	void testEquality() {
		BoolTest.testBoolValue(false, NaturalNumber.ZERO.eq(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(false, NaturalNumber.ZERO.eq(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(true, NaturalNumber.ZERO.eq(NaturalNumber.ZERO));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(2).eq(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(2).eq(ChurchObjectConverter.fromInt(3)));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(2).eq(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(1).eq(NaturalNumber.ZERO));
	}
	
	@Test
	void testGreaterThan() {
		BoolTest.testBoolValue(false, NaturalNumber.ZERO.gt(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(false, NaturalNumber.ZERO.gt(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(false, NaturalNumber.ZERO.gt(NaturalNumber.ZERO));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(2).gt(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(2).gt(ChurchObjectConverter.fromInt(3)));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(2).gt(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(1).gt(NaturalNumber.ZERO));
	}
	
	@Test
	void testLessThan() {
		BoolTest.testBoolValue(true, NaturalNumber.ZERO.lt(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(true, NaturalNumber.ZERO.lt(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(false, NaturalNumber.ZERO.lt(NaturalNumber.ZERO));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(2).lt(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(2).lt(ChurchObjectConverter.fromInt(3)));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(2).lt(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(1).lt(NaturalNumber.ZERO));
	}
	
	@Test
	void testNonEquality() {
		BoolTest.testBoolValue(true, NaturalNumber.ZERO.ne(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(true, NaturalNumber.ZERO.ne(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(false, NaturalNumber.ZERO.ne(NaturalNumber.ZERO));
		BoolTest.testBoolValue(false, ChurchObjectConverter.fromInt(2).ne(ChurchObjectConverter.fromInt(2)));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(2).ne(ChurchObjectConverter.fromInt(3)));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(2).ne(ChurchObjectConverter.fromInt(1)));
		BoolTest.testBoolValue(true, ChurchObjectConverter.fromInt(1).ne(NaturalNumber.ZERO));
	}

	private void assertIntValue(int expected, NaturalNumber n) {
		Assertions.assertEquals(expected, ChurchObjectConverter.toInt(n));
	}
}
