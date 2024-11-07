package com.pw.workflowengine.workflow.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;

import com.pw.workflowengine.stepbuilder.model.MvelExpression;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepGroup;

public class MvelAction implements Action<String, Object> {

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

    @Override
    public void execute(Map<String, Object> context, Map<String, StepGroup<String, Object>> stepGroups) {
        if(StringUtils.isBlank(mvelExpression.getExpression())) {
            return;
        }

        context.putAll(mvelExpression.getContext());

        MVEL.executeExpression(compiledExpression, context, Void.class);
    }

}
