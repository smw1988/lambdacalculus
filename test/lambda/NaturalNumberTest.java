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
		assertIntValue(0, NaturalNumber.ZERO);
		assertIntValue(0, ChurchObjectConverter.fromInt(1).dec());
		assertIntValue(1, ChurchObjectConverter.fromInt(2).dec());
	}

	private void assertIntValue(int expected, NaturalNumber n) {
		Assertions.assertEquals(expected, ChurchObjectConverter.toInt(n));
	}
}
