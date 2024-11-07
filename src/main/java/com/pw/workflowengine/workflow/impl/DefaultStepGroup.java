package com.pw.workflowengine.workflow.impl;

import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.StepGroup;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class DefaultStepGroup<K, V> implements StepGroup<K, V> {

    private String stepGroupName;
    private List<Step<K, V>> steps = null;

    public String getStepGroupName() {
        return stepGroupName;
    }

    public void setStepGroupName(String stepGroupName) {
        this.stepGroupName = stepGroupName;
    }

    public List<Step<K, V>> getSteps() {
        return steps;
    }

    public void setSteps(List<Step<K, V>> steps) {
        this.steps = steps;
    }

    public void addStep(Step<K, V> step) {
        if (CollectionUtils.isEmpty(steps)) {
            steps = new LinkedList<Step<K, V>>();
        }

        this.steps.add(step);
    }

}
