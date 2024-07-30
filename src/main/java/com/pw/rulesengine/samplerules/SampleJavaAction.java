package com.pw.rulesengine.samplerules;

import java.util.Map;

import com.pw.rulesengine.rule.impl.JavaAction;

public class SampleJavaAction implements JavaAction<Map<String, Object>>{

    @Override
    public void execute(Map<String, Object> context) {
        System.out.println("Java Rule Executed");
    }

}
