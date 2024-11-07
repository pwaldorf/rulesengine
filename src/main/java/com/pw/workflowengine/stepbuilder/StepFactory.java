package com.pw.workflowengine.stepbuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class StepFactory<K, V> {


    @SuppressWarnings("rawtypes")
    private static Map<String, ConditionBuilder> conditionBuilderCache = new ConcurrentHashMap<>();
    @SuppressWarnings("rawtypes")
    private static Map<String, ActionBuilder> actionBuilderCache = new ConcurrentHashMap<>();

    private List<ConditionBuilder<K, V>> conditionBuilders = new ArrayList<>();
    private List<ActionBuilder<K, V>> actionBuilders = new ArrayList<>();

    public StepFactory(List<ConditionBuilder<K, V>> conditionBuilders, List<ActionBuilder<K, V>> actionBuilders) {
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
    public ConditionBuilder<K, V> getConditionBuilder(String type) {
        return conditionBuilderCache.get(type);
    }

    @SuppressWarnings("unchecked")
    public ActionBuilder<K, V> getActionBuilder(String type) {
        return actionBuilderCache.get(type);
    }
}
