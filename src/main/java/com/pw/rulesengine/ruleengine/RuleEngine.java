package com.pw.rulesengine.ruleengine;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.util.RuleCache;

import lombok.extern.slf4j.Slf4j;


//Create ruleenginefactory for different rule patterns
@Slf4j
@Service
public class RuleEngine {

    private final RuleCache ruleCache;

    public RuleEngine(RuleCache ruleCache) {
        this.ruleCache = ruleCache;
    }

    public void run(String rulesetName, Map<String, Object> context) {
        Map<String, Rule> rules = ruleCache.getRuleset(rulesetName);
        if (rules != null) {
            for (Rule rule : rules.values()) {
                if (rule.getCondition().evaluate(context)) {
                    rule.getAction().execute(context);
                }
            }
        } else {
            log.info("No rules found for ruleset: {}", rulesetName);
        }
    }
}