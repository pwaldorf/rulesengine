package com.pw.workflowengine.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.pw.workflowengine.workflow.StepGroup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WorkflowCache<K, V> {

    private Map<String, Map<String, StepGroup<K, V>>> stepGroupCache = new ConcurrentHashMap<>();

    public Map<String, StepGroup<K, V>> getStepGroups(String processName) {
        return stepGroupCache.get(processName);
    }

    public void addStepGroup(String processName, StepGroup<K, V> stepGroup) {

        log.info("Loading processName: {} stepGroup: {}", processName, stepGroup.getStepGroupName());

        Map<String, StepGroup<K, V>> map = stepGroupCache.computeIfAbsent(processName, k -> new LinkedHashMap<>());
        map.put(stepGroup.getStepGroupName(), stepGroup);
    }
}
