package com.pw.workflowengine.stepbuilder;

import java.util.Map;

import com.pw.workflowengine.workflow.StepGroup;
import com.pw.workflowengine.workflow.impl.JavaAction;

public class DefaultAction<K, V> implements JavaAction<K, V> {

    @Override
    public void execute(Map<K, V> context, Map<String, StepGroup<K, V>> stepGroups) {

    }

}
