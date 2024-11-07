package com.pw.workflowengine.stepbuilder;

import com.pw.workflowengine.workflow.Condition;
import com.pw.workflowengine.workflow.StepTemplate;

public interface ConditionBuilder<K, V> {
    String getType();
    Condition<K, V> build(StepTemplate<K, V> ruleTemplate);
}
