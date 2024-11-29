package com.pw.workflowengine.stepbuilder.impl;

import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ActionBuilder;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.impl.StepGroupAction;

@Service
public class StepGroupActionBuilder<K, V> implements ActionBuilder<K, V> {

    @Override
    public Action<K, V> build(Step step) {
        return new StepGroupAction<K, V>(step.getStepObject());
    }

    @Override
    public String getType() {
        return "STEPGROUP";
    }

}
