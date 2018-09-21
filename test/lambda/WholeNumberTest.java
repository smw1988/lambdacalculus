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
		assertIntValue(1, ChurchObjectConverter.fromSignedInt(1));
		assertIntValue(2, ChurchObjectConverter.fromSignedInt(2));
		assertIntValue(3, ChurchObjectConverter.fromSignedInt(3));
		assertIntValue(-1, ChurchObjectConverter.fromSignedInt(-1));
		assertIntValue(-2, ChurchObjectConverter.fromSignedInt(-2));
		assertIntValue(-3, ChurchObjectConverter.fromSignedInt(-3));
	}
	
	static void assertIntValue(int expected, WholeNumber n) {
		Assertions.assertEquals(expected, ChurchObjectConverter.toSignedInt(n));
	}
	
	@Test
	void testIsZero() {
		ComputeFlow.ifThenElse(
				WholeNumber.ZERO.isZero(), 
				__ -> null, 
				__ -> { Assertions.fail("ZERO should be zero."); return null; });
		
		ComputeFlow.ifThenElse(
				ChurchObjectConverter.fromSignedInt(1).isZero(), 
				__ -> { Assertions.fail("ONE should not be zero."); return null; }, 
				__ -> null);
		
		ComputeFlow.ifThenElse(
				ChurchObjectConverter.fromSignedInt(-1).isZero(), 
				__ -> { Assertions.fail("ONE should not be zero."); return null; }, 
				__ -> null);
	}

}
