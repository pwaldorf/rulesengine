package com.pw.workflowengine.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.pw.workflowengine.workflow.Action;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("rawtypes")
public class ActionCache {
    private static Map<String, Action> actionCache = new ConcurrentHashMap<>();

    public static Action getAction(String actionName) {
        return actionCache.get(actionName);
    }

    public static void addAction(String actionName, Action action) {

        log.debug("Loading actionName: {}", actionName);

        // Only add the action if it does not already exist
        actionCache.computeIfAbsent(actionName, key -> {
            log.debug("Action '{}' is being added to the cache.", actionName);
            return action;
        });
    }
}
