package com.pw.workflowengine.stepbuilder;

import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Step;

public interface StepBuilder {
    Action<String, Object> build(Step step);
}
