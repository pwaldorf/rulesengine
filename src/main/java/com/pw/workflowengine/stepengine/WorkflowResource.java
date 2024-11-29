package com.pw.workflowengine.stepengine;

import java.util.List;

import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.StepGroup;

public interface WorkflowResource {

    public List<StepGroup> getStepGroupsByProcessName(String processName);

    public List<Step> getStepsByProcessName(String processName);
}
