package com.pw.workflowengine.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// import org.springframework.stereotype.Component;

import com.pw.workflowengine.workflow.StepGroup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Component
@SuppressWarnings("rawtypes")
public class WorkflowCache {

    private static Map<String, Map<String, StepGroup>> stepGroupCache = new ConcurrentHashMap<>();

    public static Map<String, StepGroup> getStepGroups(String processName) {
        return stepGroupCache.get(processName);
    }

    public static void addStepGroup(String processName, StepGroup stepGroup) {

        log.debug("Loading processName: {} stepGroup: {}", processName, stepGroup.getStepGroupName());

        Map<String, StepGroup> map = stepGroupCache.computeIfAbsent(processName, k -> new LinkedHashMap<>());
        map.put(stepGroup.getStepGroupName(), stepGroup);
    }
}
