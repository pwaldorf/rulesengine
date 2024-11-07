package com.pw.workflowengine.controller;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.pw.workflowengine.stepengine.StepEngine;

// using controller class incase a factory for different ruleengines is made

@Component
public class WorkflowController {

    private final StepEngine<String, Object> ruleEngine;

    public WorkflowController(StepEngine<String, Object> ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    // public String runRules(String rulesetName, Map<String, Object> context) {
    public String runRules(String rulesetName, Map<String, Object> context) {
        ruleEngine.run(rulesetName, context);
        return "Rules executed!";
    }
}