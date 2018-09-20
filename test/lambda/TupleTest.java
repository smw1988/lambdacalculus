package lambda;

import org.junit.jupiter.api.Test;

import churchencoding.*;

class TupleTest {

	@Test
	void testTuple() {
		Tuple<NaturalNumber, Bool> t1 = Tuple.<NaturalNumber, Bool>cons(ChurchObjectConverter.fromInt(2), Bool.FALSE);
		NaturalNumberTest.assertIntValue(2, t1.car());
		BoolTest.assertBoolValue(false, t1.cdr());
	}

}
