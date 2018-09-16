package lambda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import churchencoding.Bool;
import churchencoding.ComputeFlow;

class ComputeFlowTest {

	@Test
	void testIfThenElse() {
		Boolean result = (Boolean) ComputeFlow.ifThenElse(Bool.TRUE, __ -> true, __ -> false);
		Assertions.assertTrue(result);
		
		result = (Boolean) ComputeFlow.ifThenElse(Bool.FALSE, __ -> false, __ -> true);
		Assertions.assertTrue(result);
	}

}
