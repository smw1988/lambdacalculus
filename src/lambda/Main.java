package lambda;

import java.util.function.Function;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Function<Integer, Integer> factorial = z(f -> x -> x == 0 ? 1 : x * f.apply(x - 1));
		int result = factorial.apply(5);
		System.out.println(result);
	}
	
	private static <I, O> Function<I, O> z(Function<Function<I, O>, Function<I, O>> f) {
		return ((CallSelf<Function<I, O>>) (g -> x -> f.apply(g.apply(g)).apply(x))).apply(g -> x -> f.apply(g.apply(g)).apply(x));
	}
	

}

interface CallSelf<R> extends Function<CallSelf<R>, R> {}