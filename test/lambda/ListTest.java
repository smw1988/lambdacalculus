package lambda;

import org.junit.jupiter.api.Test;

import churchencoding.Bool;
import churchencoding.ChurchObjectConverter;
import churchencoding.ListNode;
import churchencoding.NaturalNumber;

class ListTest {

	@Test
	void testNaturalNumberListConstruction() {
		ListNode<NaturalNumber> list = ListNode.makeEmptList();
		list = list.append(ChurchObjectConverter.fromInt(1));
		list = list.append(NaturalNumber.ZERO);
		
		BoolTest.assertBoolValue(false, list.isNil());
		BoolTest.assertBoolValue(false, list.cdr().isNil());
		BoolTest.assertBoolValue(true, list.cdr().cdr().isNil());
	}
	
	@Test
	void testBoolListConstruction() {
		ListNode<Bool> list = ListNode.makeEmptList();
		list = list.append(Bool.FALSE);
		list = list.append(Bool.TRUE);
		list = list.append(Bool.FALSE);
		
		BoolTest.assertBoolValue(false, list.isNil());
		BoolTest.assertBoolValue(false, list.cdr().isNil());
		BoolTest.assertBoolValue(false, list.cdr().cdr().isNil());
		BoolTest.assertBoolValue(true, list.cdr().cdr().cdr().isNil());
	}

}
