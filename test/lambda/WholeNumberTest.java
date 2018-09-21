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
	
	private static WholeNumber fromSignedInt(int n) {
		return ChurchObjectConverter.fromSignedInt(n);
	}

	static void assertIntValue(int expected, WholeNumber n) {
		Assertions.assertEquals(expected, ChurchObjectConverter.toSignedInt(n));
	}
	
}
