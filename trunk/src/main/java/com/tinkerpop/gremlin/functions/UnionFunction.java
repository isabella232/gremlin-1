package com.tinkerpop.gremlin.functions;

import com.tinkerpop.gremlin.FunctionHelper;
import com.tinkerpop.gremlin.GremlinFunctions;
import com.tinkerpop.gremlin.statements.EvaluationException;
import org.apache.commons.jxpath.ExpressionContext;
import org.apache.commons.jxpath.Function;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnionFunction implements Function {

    public static final String FUNCTION_NAME = "union";

    public Set invoke(ExpressionContext context, Object[] parameters) {

        Object[] objects = FunctionHelper.nodeSetConversion(parameters);
        if (null != objects && objects.length > 1) {
            Set set = new HashSet();
            for (Object object : objects) {
                if (object instanceof Collection)
                    set.addAll((Collection) object);
                else if (!(object instanceof Map))
                    set.add(object);

            }
            return set;
        }
        throw EvaluationException.createException(FunctionHelper.makeFunctionName(GremlinFunctions.NAMESPACE_PREFIX,FUNCTION_NAME), EvaluationException.EvaluationErrorType.UNSUPPORTED_PARAMETERS);

    }
}