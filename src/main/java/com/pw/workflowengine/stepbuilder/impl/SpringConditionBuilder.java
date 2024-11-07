package com.pw.workflowengine.stepbuilder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ConditionBuilder;
import com.pw.workflowengine.workflow.Condition;
import com.pw.workflowengine.workflow.StepTemplate;
import com.pw.workflowengine.workflow.impl.SpringCondition;

@Service
public class SpringConditionBuilder implements ConditionBuilder<String, Object> {

    private final ApplicationContext applicationContext;

    public SpringConditionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Condition<String, Object> build(StepTemplate<String, Object> ruleTemplate) {
        return new SpringCondition<String, Object>(applicationContext, ruleTemplate.getSpringMethod());
    }

    @Override
    public String getType() {
        return "SPRING";
    }

}
