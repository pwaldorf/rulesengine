package com.pw.workflowengine.sampleworkflow;

import java.util.Map;

import com.pw.workflowengine.workflow.StepGroup;
import com.pw.workflowengine.workflow.impl.JavaAction;

public class SampleJavaAction extends JavaAction<String, Object> {

    @Override
    public boolean execute(Map<String, Object> context, Map<String, StepGroup> stepGroups) {
        // System.out.println("Java Rule Executed");
        return true;
    }

}
