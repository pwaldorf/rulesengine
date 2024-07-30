package com.pw.rulesengine.rule.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;

import com.pw.rulesengine.rule.Action;

public class MvelAction<U> implements Action<U> {

    private final String expression;
    private final Map<String, Object> context;

    private Serializable compiledExpression;

    public  MvelAction(String expression, Map<String, Object> context) {
        this.expression = expression;
        this.context = context;

        this.compiledExpression = StringUtils.isNotBlank(expression) ? MVEL.compileExpression(expression) : null;

    }

    @Override
    public String getType() {
        return "MVEL";
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute(U u) {
        if(StringUtils.isBlank(expression)) {
            return;
        }
        if (u instanceof Map) {
            ((Map<String, Object>) u).putAll(context);
        }
        MVEL.executeExpression(compiledExpression, u, Void.class);
    }
}
