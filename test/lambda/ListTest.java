package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
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
	
	@Test
	void testConversion() {
		List<Integer> list = Arrays.asList(1, 0, 2);
		List<NaturalNumber> numberList = list.stream().map(ChurchObjectConverter::fromInt).collect(Collectors.toList());
		
		assertList(list, ChurchObjectConverter.fromList(numberList), ChurchObjectConverter::toInt);
	}
	
	private static <R, T> void assertList(List<R> expected, ListNode<T> list, Function<T, R> convert) {
		List<T> actualList = ChurchObjectConverter.toList(list);
		Assertions.assertEquals(expected.size(), actualList.size());
		
		for (int i = 0; i < expected.size(); ++i) {
			Assertions.assertEquals(expected.get(i), convert.apply(actualList.get(i)));
		}
	}

}
