package com.pw.workflowengine.workflow;

import java.util.Map;

public interface Action<K, V> {
    String getType();
    String getActionName();
    boolean execute(Map<K, V> context, Map<String, StepGroup> stepGroups);
}
