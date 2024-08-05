package com.pw.rulesengine.controller;

import org.springframework.stereotype.Component;
import com.pw.rulesengine.ruleengine.RuleEngine;

// using controller class incase a factory for different ruleengines is made

@Component
public class RulesController<T, U> {

    private final RuleEngine<T, U> ruleEngine;

    public RulesController(RuleEngine<T, U> ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    // public String runRules(String rulesetName, Map<String, Object> context) {
    public String runRules(T t, U u) {
        ruleEngine.run(t, u);
        return "Rules executed!";
    }
}