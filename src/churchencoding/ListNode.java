package churchencoding;

import java.util.function.Function;

public interface ListNode<T> extends Tuple<T, ListNode<T>> {

	public static final ListNode<?> NIL = f -> Bool.FALSE.apply(f);
	
	@SuppressWarnings("unchecked")
	public static <T> ListNode<T> makeEmptList() {
		return (ListNode<T>) NIL;
	}
	
	static <T> ListNode<T> makeListNode(T value, ListNode<T> next) {
		return f -> f.apply(value).apply(next);
	}
	
	@SuppressWarnings("unchecked")
	default Bool isNil() {
		return (((Function<Bool, Bool>)this.apply(v -> n -> (Function<Bool, Bool>) (x -> Bool.FALSE)))).apply(Bool.TRUE);
	}
	
	default ListNode<T> append(T value) {
		return makeListNode(value, this);
	}
}
