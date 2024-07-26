package com.pw.rulesengine.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pw.rulesengine.rule.builder.RuleBuilder;

import jakarta.annotation.PostConstruct;

@Component
public class RuleFactory {

    private static Map<String, RuleBuilder> ruleBuilderCache = new ConcurrentHashMap<>();

    private List<RuleBuilder> ruleBuilders = new ArrayList<>();

    public RuleFactory(List<RuleBuilder> ruleBuilders) {
        this.ruleBuilders = ruleBuilders;
    }

    @PostConstruct
    void initCache() {
        ruleBuilders.forEach(ruleBuilder -> ruleBuilderCache.put(
            ruleBuilder.getType(), ruleBuilder
        ));
    }

    public RuleBuilder getRuleBuilder(String type) {
        return ruleBuilderCache.get(type);
    }


}
