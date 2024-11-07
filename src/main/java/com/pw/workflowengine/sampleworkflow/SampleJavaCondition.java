package com.pw.workflowengine.sampleworkflow;

import java.util.Map;
import com.pw.workflowengine.workflow.impl.JavaCondition;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SampleJavaCondition implements JavaCondition<String, Object> {

    @Override
    public boolean evaluate(Map<String, Object> context) {
        return (context.get("animal")).toString().equals("tiger");
    }

}
