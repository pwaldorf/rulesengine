package com.pw.rulesengine.ruleengine;

import java.util.Map;

import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.util.RuleCache;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConditionalRuleEngine implements RuleEngine<String, Map<String, Object>> {

    private final RuleCache ruleCache;

    public ConditionalRuleEngine(RuleCache ruleCache) {
        this.ruleCache = ruleCache;
    }

    public void run(String rulesetName, Map<String, Object> context) {
        Map<String, Rule> rules = ruleCache.getRuleset(rulesetName);
        if (rules != null) {
            for (Rule rule : rules.values()) {
                if (rule.getCondition().evaluate(context)) {
                    rule.getAction().execute(context);
                } else {
                    rule.getAction().execute(context);
                }

            }
        } else {
            log.info("No rules found for ruleset: {}", rulesetName);
        }
    }

}
