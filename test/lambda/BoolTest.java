package lambda;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import churchencoding.Bool;
import churchencoding.ChurchObjectConverter;

class BoolTest {

	@Test
	void testConversion() {
		assertBoolValue(true, Bool.TRUE);
		assertBoolValue(false, Bool.FALSE);
		
		assertBoolValue(true, ChurchObjectConverter.fromBoolean(true));
		assertBoolValue(false, ChurchObjectConverter.fromBoolean(false));
	}
	
	@Test
	void testLogicalAdd() {
		assertBoolValue(true, and(Bool.TRUE, Bool.TRUE));
		assertBoolValue(false, and(Bool.TRUE, Bool.FALSE));
		assertBoolValue(false, and(Bool.FALSE, Bool.TRUE));
		assertBoolValue(false, and(Bool.FALSE, Bool.FALSE));
	}
	
	@Test
	void testLogicalOr() {
		assertBoolValue(true, or(Bool.TRUE, Bool.TRUE));
		assertBoolValue(true, or(Bool.TRUE, Bool.FALSE));
		assertBoolValue(true, or(Bool.FALSE, Bool.TRUE));
		assertBoolValue(false, or(Bool.FALSE, Bool.FALSE));
	}
	
	@Test
	void testLogicalNot() {
		assertBoolValue(false, not(Bool.TRUE));
		assertBoolValue(true, not(Bool.FALSE));
	}
	
	@Test
	void testLogicalXor() {
		assertBoolValue(false, xor(Bool.TRUE, Bool.TRUE));
		assertBoolValue(true, xor(Bool.TRUE, Bool.FALSE));
		assertBoolValue(true, xor(Bool.FALSE, Bool.TRUE));
		assertBoolValue(false, xor(Bool.FALSE, Bool.FALSE));
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
	
	static void assertBoolValue(boolean expected, Bool bool) {
		Assertions.assertEquals(expected, ChurchObjectConverter.toBoolean(bool));
	}

}
