package com.pw.rulesengine.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pw.rulesengine.rule.Rule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RuleCache {

    private Map<String, Map<String, Rule>> rulesetCache = new ConcurrentHashMap<>();

    public Map<String, Rule> getRuleset(String rulesetName) {
        return rulesetCache.get(rulesetName);
    }

    public void addRuleset(String rulesetName, String ruleId, Rule rule) {

        log.info("Loading ruleset: {} ruleId: {}", rulesetName, ruleId);

        Map<String, Rule> map = rulesetCache.computeIfAbsent(rulesetName, k -> new LinkedHashMap<>());
        map.put(ruleId, rule);
    }
}
