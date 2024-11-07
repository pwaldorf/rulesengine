package com.pw.workflowengine.stepbuilder;

import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.StepTemplate;

public interface StepBuilder<K, V> {
    Step<K, V> build(StepTemplate<K, V> ruleTemplate);
}
