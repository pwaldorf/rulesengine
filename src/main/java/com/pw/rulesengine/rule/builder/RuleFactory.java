package com.pw.rulesengine.rule.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class RuleFactory {

    private static Map<String, ConditionBuilder> conditionBuilderCache = new ConcurrentHashMap<>();
    private static Map<String, ActionBuilder> actionBuilderCache = new ConcurrentHashMap<>();

    private List<ConditionBuilder> conditionBuilders = new ArrayList<>();
    private List<ActionBuilder> actionBuilders = new ArrayList<>();

    public RuleFactory(List<ConditionBuilder> conditionBuilders, List<ActionBuilder> actionBuilders) {
        this.conditionBuilders = conditionBuilders;
        this.actionBuilders = actionBuilders;
    }

    @PostConstruct
    void initCache() {
        conditionBuilders.forEach(conditionBuilder -> conditionBuilderCache.put(
            conditionBuilder.getType(), conditionBuilder
        ));
        actionBuilders.forEach(actionBuilder -> actionBuilderCache.put(
            actionBuilder.getType(), actionBuilder
        ));
    }

    public ConditionBuilder getConditionBuilder(String type) {
        return conditionBuilderCache.get(type);
    }

    public ActionBuilder getActionBuilder(String type) {
        return actionBuilderCache.get(type);
    }
}
