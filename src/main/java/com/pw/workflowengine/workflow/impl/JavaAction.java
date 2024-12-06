package com.pw.workflowengine.workflow.impl;

import com.pw.workflowengine.workflow.Action;

public abstract class JavaAction<K, V> implements Action<K, V> {

    @Override
    public String getType() {
         return "JAVA";
    }

}