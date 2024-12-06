package com.pw.workflowengine.workflow.impl;

import java.io.Serializable;
import java.util.Map;

import org.mvel2.MVEL;

import com.pw.workflowengine.stepbuilder.model.MvelExpression;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepGroup;

public class MvelAction implements Action<String, Object> {

    private final MvelExpression mvelExpression;

    private Serializable compiledExpression;

    public  MvelAction(MvelExpression mvelExpression) {
        this.mvelExpression = mvelExpression;
        this.compiledExpression = MVEL.compileExpression(mvelExpression.getExpression());
    }

    @Override
    public String getType() {
        return "MVEL";
    }

    @Override
    public boolean execute(Map<String, Object> context, Map<String, StepGroup> stepGroups) {
        return MVEL.executeExpression(compiledExpression, mvelExpression.getContext(), context, Boolean.class);
    }

}
