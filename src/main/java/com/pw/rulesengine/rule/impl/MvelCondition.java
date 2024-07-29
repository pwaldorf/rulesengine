package com.pw.rulesengine.rule.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;

import com.pw.rulesengine.rule.Condition;

public class MvelCondition<T> implements Condition<T> {

    private final String expression;
    private final Map<String, Object> context;

    private Serializable compiledEvaluateExpression;

    public MvelCondition(String expression, Map<String, Object> context) {
        this.expression = expression;
        this.context = context;

        this.compiledEvaluateExpression = StringUtils.isNotBlank(expression) ? MVEL.compileExpression(expression) : null;
    }

    @Override
    public String getType() {
        return "MVEL";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean evaluate(T o) {
        if (StringUtils.isBlank(expression)) {
            return true;
        }
        if (o instanceof Map) {
            ((Map<String, Object>) o).putAll(context);
        }
        return MVEL.executeExpression(compiledEvaluateExpression, o, Boolean.class);
    }

}
