package com.pw.rulesengine.rule.impl;

import java.io.Serializable;

import org.mvel2.MVEL;
import com.pw.rulesengine.rule.Rule;

import lombok.Getter;

@Getter
public class MvelRule<T, U> implements Rule<T, U> {

    private final String evaluateExpression;
    private final String executeExpression;

    private Serializable compiledEvaluateExpression;
    private Serializable compiledExecuteExpression;

    public MvelRule(String evaluateExpression, String executeExpression) {
        this.evaluateExpression = evaluateExpression;
        this.compiledEvaluateExpression = MVEL.compileExpression(evaluateExpression);
        this.executeExpression = executeExpression;
        this.compiledExecuteExpression = MVEL.compileExpression(executeExpression);
    }

    @Override
    public String getType() {
        return "MVEL";
    }

    @Override
    public boolean evaluate(T t) {
        return MVEL.executeExpression(compiledEvaluateExpression, t, Boolean.class);
    }

    @Override
    public void execute(U u) {
        MVEL.executeExpression(compiledExecuteExpression, u, Void.class);
    }
}