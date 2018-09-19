package churchencoding;

import java.util.function.Function;

public class ComputeFlow {

	public static <R> R ifThenElse(Bool condition, 
								    Function<Function<Object, Object>, R> thenFactory, 
								    Function<Function<Object, Object>, R> elseFactory)
	{
		@SuppressWarnings("unchecked")
		Function<Function<Object, Object>, R> branchToExecute = (Function<Function<Object, Object>, R>) 
				condition.apply(thenFactory).apply(elseFactory);
		
		return branchToExecute.apply(FunctionUtility::id);
	}
	
}
