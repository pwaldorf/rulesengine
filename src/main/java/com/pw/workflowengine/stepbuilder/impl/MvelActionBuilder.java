package com.pw.workflowengine.stepbuilder.impl;

import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ActionBuilder;
import com.pw.workflowengine.stepbuilder.model.MvelExpression;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepTemplate;
import com.pw.workflowengine.workflow.impl.MvelAction;

@Service
public class MvelActionBuilder implements ActionBuilder<String, Object> {

    @Override
    public String getType() {
        return "MVEL";
    }

    @Override
    public Action<String, Object> build(String type, StepTemplate<String, Object> ruleTemplate) {
        MvelExpression mvelExpression = new MvelExpression();
        if (type.toUpperCase().equals("PASS")) {
            mvelExpression.setExpression(ruleTemplate.getExpression().getPassExpression());
            mvelExpression.setContext(ruleTemplate.getExpression().getPassContext());
            return new MvelAction(mvelExpression);
        }

        if (type.toUpperCase().equals("FAIL")) {
            mvelExpression.setExpression(ruleTemplate.getExpression().getFailExpression());
            mvelExpression.setContext(ruleTemplate.getExpression().getFailContext());
            return new MvelAction(mvelExpression);
        }

        mvelExpression.setExpression(ruleTemplate.getExpression().getAlwaysExpression());
        mvelExpression.setContext(ruleTemplate.getExpression().getAlwaysContext());
        return new MvelAction(mvelExpression);
    }
}
