package com.pw.workflowengine.stepbuilder.impl;

// import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.pw.workflowengine.stepbuilder.StepBuilder;
import com.pw.workflowengine.stepbuilder.StepFactory;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Step;

@Component
public class DefaultStepBuilder implements StepBuilder {

    private final StepFactory<String, Object> ruleFactory;

    public DefaultStepBuilder(StepFactory<String, Object> ruleFactory) {
        this.ruleFactory = ruleFactory;
    }

    @Override
    public Action<String, Object> build(Step step) {
        return ruleFactory.getActionBuilder(step.getStepType()).build(step);
    }
}
