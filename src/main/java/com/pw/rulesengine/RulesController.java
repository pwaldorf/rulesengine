package com.pw.rulesengine;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.pw.rulesengine.ruleengine.RuleEngine;

@Service
public class RulesController {

    private final RuleEngine ruleEngine;

    public RulesController(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    public String runRules(String rulesetName, Map<String, Object> context) {
        ruleEngine.run(rulesetName, context);
        return "Rules executed!";
    }
}