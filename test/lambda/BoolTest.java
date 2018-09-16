package lambda;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import churchencoding.Bool;
import churchencoding.ChurchObjectConverter;
import churchencoding.LogicOperations;


class BoolTest {

	@Test
	void testConversion() {
		testBoolValue(true, Bool.TRUE);
		testBoolValue(false, Bool.FALSE);
		
		testBoolValue(true, ChurchObjectConverter.fromBoolean(true));
		testBoolValue(false, ChurchObjectConverter.fromBoolean(false));
	}
	
	@Test
	void testLogicalAdd() {
		testBoolValue(true, LogicOperations.and(Bool.TRUE, Bool.TRUE));
		testBoolValue(false, LogicOperations.and(Bool.TRUE, Bool.FALSE));
		testBoolValue(false, LogicOperations.and(Bool.FALSE, Bool.TRUE));
		testBoolValue(false, LogicOperations.and(Bool.FALSE, Bool.FALSE));
	}
	
	void testBoolValue(boolean expected, Bool bool) {
		Assertions.assertEquals(expected, ChurchObjectConverter.toBoolean(bool));
	}

}
