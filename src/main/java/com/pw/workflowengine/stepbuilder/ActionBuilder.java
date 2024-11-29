package com.pw.workflowengine.stepbuilder;

import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Step;

public interface ActionBuilder<K, V> {
    String getType();
    Action<K, V> build(Step step);
}
