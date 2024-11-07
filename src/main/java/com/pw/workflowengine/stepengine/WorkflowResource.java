package com.pw.workflowengine.stepengine;

import java.util.List;

import com.pw.workflowengine.workflow.StepTemplate;

public interface WorkflowResource<K, V> {

    public List<StepTemplate<K, V>> getAllSteps();
    public List<StepTemplate<K, V>> getAllStepsByProcessName(String processName);
}
