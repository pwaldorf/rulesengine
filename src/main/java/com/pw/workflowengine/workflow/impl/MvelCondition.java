package com.pw.workflowengine.workflow.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;

import com.pw.workflowengine.workflow.Condition;

public class MvelCondition implements Condition<String, Object> {

    private final String expression;
    private final Map<String, Object> mvelContext;

    private Serializable compiledEvaluateExpression;

    public MvelCondition(String expression, Map<String, Object> mvelContext) {
        this.expression = expression;
        this.mvelContext = mvelContext;

        this.compiledEvaluateExpression = StringUtils.isNotBlank(expression) ? MVEL.compileExpression(expression) : null;
    }

    @Override
    public String getType() {
        return "MVEL";
    }

    @Override
    public boolean evaluate(Map<String, Object> context) {
        if (StringUtils.isBlank(expression)) {
            return true;
        }
        context.putAll(mvelContext);

        return MVEL.executeExpression(compiledEvaluateExpression, context, Boolean.class);
    }

}
