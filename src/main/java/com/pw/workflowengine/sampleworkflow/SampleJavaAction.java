package com.pw.workflowengine.sampleworkflow;

import java.util.Map;

import com.pw.workflowengine.workflow.StepGroup;
import com.pw.workflowengine.workflow.impl.JavaAction;

public class SampleJavaAction implements JavaAction<String, Object>{

    @Override
    public void execute(Map<String, Object> context, Map<String, StepGroup<String, Object>> stepGroups) {
        System.out.println("Java Rule Executed");
    }

}
