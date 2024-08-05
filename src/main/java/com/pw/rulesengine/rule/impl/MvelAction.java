package com.pw.rulesengine.rule.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rulebuilder.model.MvelExpression;

public class MvelAction<U> implements Action<U> {

    private final MvelExpression mvelExpression;

    private Serializable compiledExpression;

    public  MvelAction(MvelExpression mvelExpression) {
        this.mvelExpression = mvelExpression;

        this.compiledExpression = StringUtils.isNotBlank(mvelExpression.getExpression())
                                ? MVEL.compileExpression(mvelExpression.getExpression()) : null;

    }

    @Override
    public String getType() {
        return "MVEL";
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute(U u) {
        if(StringUtils.isBlank(mvelExpression.getExpression())) {
            return;
        }
        if (u instanceof Map) {
            ((Map<String, Object>) u).putAll(mvelExpression.getContext());
        }
        MVEL.executeExpression(compiledExpression, u, Void.class);
    }
}
