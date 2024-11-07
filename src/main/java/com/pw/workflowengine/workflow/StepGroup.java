package com.pw.workflowengine.workflow;

import java.util.List;

public interface StepGroup<K, V> {

    String getStepGroupName();
    void setStepGroupName(String stepGroupName);

    List<Step<K, V>> getSteps();
    void setSteps(List<Step<K, V>> steps);
    void addStep(Step<K, V> step);

}
