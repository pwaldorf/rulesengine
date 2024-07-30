package com.pw.rulesengine.controller;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.pw.rulesengine.ruleengine.DefaultRuleEngine;

// using controller class incase a factory for different ruleengines is made

@Service
public class RulesController {

    private final DefaultRuleEngine ruleEngine;

    public RulesController(DefaultRuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    public String runRules(String rulesetName, Map<String, Object> context) {
        ruleEngine.run(rulesetName, context);
        return "Rules executed!";
    }
}