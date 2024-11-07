package com.pw.workflowengine.stepengine;

import java.util.Map;

public interface StepEngine<K, V> {
    void run(String processName, Map<K, V> context);
}
