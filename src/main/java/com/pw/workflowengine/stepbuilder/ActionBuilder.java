package com.pw.workflowengine.stepbuilder;

import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepTemplate;

public interface ActionBuilder<K, V> {
    String getType();
    Action<K, V> build(String Type, StepTemplate<K, V> ruleTemplate);
}
