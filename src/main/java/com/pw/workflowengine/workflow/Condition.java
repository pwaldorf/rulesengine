package com.pw.workflowengine.workflow;

import java.util.Map;

public interface Condition<K, V> extends Definition{
    boolean evaluate(Map<K, V> context);
}
