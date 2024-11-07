package com.pw.workflowengine.stepbuilder.impl;

import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ConditionBuilder;
import com.pw.workflowengine.workflow.Condition;
import com.pw.workflowengine.workflow.StepTemplate;
import com.pw.workflowengine.workflow.impl.MvelCondition;

@Service
public class MvelConditionBuilder implements ConditionBuilder<String, Object> {

    public Condition<String, Object> build(StepTemplate<String, Object> ruleTemplate) {
        return new MvelCondition(ruleTemplate.getExpression().getEvaluateExpression(), ruleTemplate.getExpression().getEvaluateContext());
    }

    @Override
    public String getType() {
        return "MVEL";
    }
}
