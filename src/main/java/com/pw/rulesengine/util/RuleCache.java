package com.pw.rulesengine.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.rule.RuleFactory;
import com.pw.rulesengine.ruleengine.KnowledgeBaseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RuleCache {

    public static Map<String, Map<String, Rule>> rulesetCache = new ConcurrentHashMap<>();

    private final RuleFactory ruleFactory;
    private final KnowledgeBaseRepository knowledgeBaseRepository;

    public RuleCache(RuleFactory ruleFactory, KnowledgeBaseRepository knowledgeBaseRepository) {
        this.ruleFactory = ruleFactory;
        this.knowledgeBaseRepository = knowledgeBaseRepository;
    }

    public Map<String, Rule> getRuleset(String rulesetName) {
        return rulesetCache.computeIfAbsent(rulesetName, this::loadRuleset);
    }

    private Map<String, Rule> loadRuleset(String rulesetName) {

        log.info("Loading ruleset: {}", rulesetName);
        Map<String, Rule> rules = new ConcurrentHashMap<>();

        knowledgeBaseRepository.getAllRuleByRuleSetName(rulesetName).forEach(ruleTemplate -> {
            rules.put(ruleTemplate.getRuleId(), ruleFactory.getRuleBuilder(ruleTemplate.getType().toUpperCase()).build(ruleTemplate));
        });;

        return rules;
    }

    public void clearCache() {
        rulesetCache.clear();
    }

    public void evictRuleset(String rulesetName) {
        rulesetCache.remove(rulesetName);
    }

    public void evictRule(String rulesetName, String ruleId) {
        rulesetCache.get(rulesetName).remove(ruleId);
    }
}
