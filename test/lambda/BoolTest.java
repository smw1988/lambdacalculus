package lambda;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import churchencoding.Bool;
import churchencoding.ChurchObjectConverter;

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
		testBoolValue(true, and(Bool.TRUE, Bool.TRUE));
		testBoolValue(false, and(Bool.TRUE, Bool.FALSE));
		testBoolValue(false, and(Bool.FALSE, Bool.TRUE));
		testBoolValue(false, and(Bool.FALSE, Bool.FALSE));
	}
	
	@Test
	void testLogicalOr() {
		testBoolValue(true, or(Bool.TRUE, Bool.TRUE));
		testBoolValue(true, or(Bool.TRUE, Bool.FALSE));
		testBoolValue(true, or(Bool.FALSE, Bool.TRUE));
		testBoolValue(false, or(Bool.FALSE, Bool.FALSE));
	}
	
	@Test
	void testLogicalNot() {
		testBoolValue(false, not(Bool.TRUE));
		testBoolValue(true, not(Bool.FALSE));
	}
	
	@Test
	void testLogicalXor() {
		testBoolValue(false, xor(Bool.TRUE, Bool.TRUE));
		testBoolValue(true, xor(Bool.TRUE, Bool.FALSE));
		testBoolValue(true, xor(Bool.FALSE, Bool.TRUE));
		testBoolValue(false, xor(Bool.FALSE, Bool.FALSE));
	}
	
	private Bool and(Bool b1, Bool b2) {
		return b1.and(b2);
	}
	
	private Bool or(Bool b1, Bool b2) {
		return b1.or(b2);
	}
	
	private Bool not(Bool b) {
		return b.not();
	}
	
	private Bool xor(Bool b1, Bool b2) {
		return b1.xor(b2);
	}
	
	static void testBoolValue(boolean expected, Bool bool) {
		Assertions.assertEquals(expected, ChurchObjectConverter.toBoolean(bool));
	}

}
