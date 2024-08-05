package com.pw.rulesengine.ruleengine.engine;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.ruleengine.RuleEngine;
import com.pw.rulesengine.ruleengine.RuleRepository;

import lombok.extern.slf4j.Slf4j;

//Create ruleenginefactory for different rule patterns
@Slf4j
@Component
public class SimpleRuleEngine implements RuleEngine<String, Map<String, Object>> {

    private final RuleRepository ruleRepository;

    public SimpleRuleEngine(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void run(String rulesetName, Map<String, Object> context) {
        Map<String, Rule> rules = ruleRepository.getByRuleset(rulesetName);
        if (rules != null) {
            for (Rule rule : rules.values()) {
                rule.getStatement().execute(rule, context);
            }
        } else {
            log.info("No rules found for ruleset: {}", rulesetName);
        }
    }

}