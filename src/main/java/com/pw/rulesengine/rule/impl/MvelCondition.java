package com.pw.rulesengine.rule.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;

import com.pw.rulesengine.models.DefaultExpression;
import com.pw.rulesengine.rule.Condition;

public class MvelCondition<T> implements Condition<T> {

    private final DefaultExpression expression;

    private Serializable compiledEvaluateExpression;

    public MvelCondition(DefaultExpression expression) {
        this.expression = expression;

        this.compiledEvaluateExpression = StringUtils.isNotBlank(expression.getEvaluateExpression()) ? MVEL.compileExpression(expression.getEvaluateExpression()) : null;
    }

    @Override
    public String getConditionType() {
        return "MVEL";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean evaluate(T o) {
        if (StringUtils.isBlank(expression.getEvaluateExpression())) {
            return true;
        }
        if (o instanceof Map) {
            ((Map<String, Object>) o).putAll(expression.getContext());
        }
        return MVEL.executeExpression(compiledEvaluateExpression, o, Boolean.class);
    }

}
