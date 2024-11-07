package com.pw.workflowengine.workflow;

import java.util.Map;

public interface Action<K, V> extends Definition {
    void execute(Map<K, V> context, Map<String, StepGroup<K, V>> stepGroups);
}
