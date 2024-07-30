package com.pw.rulesengine.rule.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class RuleFactory<T, U> {

    @SuppressWarnings("rawtypes")
    private static Map<String, ConditionBuilder> conditionBuilderCache = new ConcurrentHashMap<>();
    @SuppressWarnings("rawtypes")
    private static Map<String, ActionBuilder> actionBuilderCache = new ConcurrentHashMap<>();

    private List<ConditionBuilder<T>> conditionBuilders = new ArrayList<>();
    private List<ActionBuilder<U>> actionBuilders = new ArrayList<>();

    public RuleFactory(List<ConditionBuilder<T>> conditionBuilders, List<ActionBuilder<U>> actionBuilders) {
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

    @SuppressWarnings("unchecked")
    public ConditionBuilder<T> getConditionBuilder(String type) {
        return conditionBuilderCache.get(type);
    }

    @SuppressWarnings("unchecked")
    public ActionBuilder<U> getActionBuilder(String type) {
        return actionBuilderCache.get(type);
    }
}
