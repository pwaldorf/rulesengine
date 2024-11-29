package com.pw.workflowengine.stepbuilder.impl;

import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ActionBuilder;
import com.pw.workflowengine.stepbuilder.model.MvelExpression;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.impl.MvelAction;

@Service
public class MvelActionBuilder implements ActionBuilder<String, Object> {

    @Override
    public String getType() {
        return "MVEL";
    }

    @Override
    public Action<String, Object> build(Step step) {
        MvelExpression mvelExpression = new MvelExpression();
        mvelExpression.setExpression(step.getStepObject());
        mvelExpression.setContext(step.getStepContext());
        return new MvelAction(mvelExpression);
    }
}
