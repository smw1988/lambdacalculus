package churchencoding;

import java.util.function.Function;

public class ComputeFlow {

	public static Object ifThenElse(Bool condition, 
								    Function<Function<Object, Object>, Object> thenFactory, 
								    Function<Function<Object, Object>, Object> elseFactory)
	{
		@SuppressWarnings("unchecked")
		Function<Function<Object, Object>, Object> branchToExecute = (Function<Function<Object, Object>, Object>) 
				condition.apply(thenFactory).apply(elseFactory);
		
		return branchToExecute.apply(FunctionUtility::id);
	}
	
}
