package com.pw.workflowengine.stepbuilder.impl;

import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ActionBuilder;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepTemplate;
import com.pw.workflowengine.workflow.impl.StepGroupAction;

@Service
public class StepGroupActionBuilder<K, V> implements ActionBuilder<K, V> {

    @Override
    public Action<K, V> build(String type, StepTemplate<K, V> ruleTemplate) {
        String className;

        switch (type.toUpperCase()) {
            case "PASS":
                className = ruleTemplate.getJavaClassName().getPassClassName();
                break;
            case "FAIL":
                className = ruleTemplate.getJavaClassName().getFailClassName();
                break;
            default:
                className = ruleTemplate.getJavaClassName().getAlwaysClassName();
                break;
        }

        // Ensure type safety with StepGroupAction using the class name
        return new StepGroupAction<K, V>(className.toUpperCase());
    }

    @Override
    public String getType() {
        return "STEPGROUP";
    }

}
