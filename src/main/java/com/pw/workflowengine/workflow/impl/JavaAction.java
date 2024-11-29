package com.pw.workflowengine.workflow.impl;

import com.pw.workflowengine.workflow.Action;

public abstract class JavaAction<K, V> implements Action<K, V> {

    private String actionName;

    @Override
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String getType() {
         return "JAVA";
    }

}