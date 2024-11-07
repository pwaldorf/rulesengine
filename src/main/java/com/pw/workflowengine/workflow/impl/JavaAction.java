package com.pw.workflowengine.workflow.impl;

import com.pw.workflowengine.workflow.Action;

public interface JavaAction<K, V> extends Action<K, V> {

    @Override
    default public String getType() {
         return "JAVA";
    }
}