package com.pw.rulesengine.rule.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;

import com.pw.rulesengine.models.DefaultExpression;
import com.pw.rulesengine.rule.Action;

public class MvelAction<U> implements Action<U> {

    private final DefaultExpression expression;

    private Serializable compiledPassExpression;
    private Serializable compiledFailExpression;
    private Serializable compiledAlwaysExpression;

    public  MvelAction(DefaultExpression expression) {
        this.expression = expression;

        this.compiledPassExpression = StringUtils.isNotBlank(expression.getPassExpression()) ? MVEL.compileExpression(expression.getPassExpression()) : null;
        this.compiledFailExpression = StringUtils.isNotBlank(expression.getFailExpression()) ? MVEL.compileExpression(expression.getFailExpression()) : null;
        this.compiledAlwaysExpression = StringUtils.isNotBlank(expression.getAlwaysExpression()) ? MVEL.compileExpression(expression.getAlwaysExpression()) : null;
    }


    @Override
    public String getActionType() {
        return "MVEL";
    }

    @SuppressWarnings("unchecked")
    @Override
    public void pass(U u) {
        if(StringUtils.isBlank(expression.getPassExpression())) {
            return;
        }
        if (u instanceof Map) {
            ((Map<String, Object>) u).putAll(expression.getContext());
        }
        MVEL.executeExpression(compiledPassExpression, u, Void.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void fail(U u) {
        if(StringUtils.isBlank(expression.getFailExpression())) {
            return;
        }
        if (u instanceof Map) {
            ((Map<String, Object>) u).putAll(expression.getContext());
        }
        MVEL.executeExpression(compiledFailExpression, u, Void.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void always(U u) {
        if(StringUtils.isBlank(expression.getAlwaysExpression())) {
            return;
        }
        if (u instanceof Map) {
            ((Map<String, Object>) u).putAll(expression.getContext());
        }
        MVEL.executeExpression(compiledAlwaysExpression, u, Void.class);
    }

}
