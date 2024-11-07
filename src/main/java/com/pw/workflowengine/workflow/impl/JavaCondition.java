package com.pw.workflowengine.workflow.impl;

import com.pw.workflowengine.workflow.Condition;

public interface JavaCondition<K, V> extends Condition<K, V> {

    default String getType() {
        return "JAVA";
    }

}
