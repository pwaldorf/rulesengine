package com.pw.workflowengine.stepengine;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import com.pw.workflowengine.stepbuilder.StepBuilder;
import com.pw.workflowengine.util.WorkflowCache;
import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.StepGroup;
import com.pw.workflowengine.workflow.impl.DefaultStepGroup;

@Component
public class StepRepository<K, V> {

    private final WorkflowResource<K, V> workflowResource;
    private final StepBuilder<K, V> stepBuilder;
    private final WorkflowCache<K, V> stepGroupCache;

    public StepRepository(WorkflowResource<K, V> workflowResource, StepBuilder< K, V> stepBuilder, WorkflowCache<K, V> stepGroupCache) {
        this.workflowResource = workflowResource;
        this.stepBuilder = stepBuilder;
        this.stepGroupCache = stepGroupCache;
    }

    public Map<String, StepGroup<K, V>> getByProcess(String processName) {

        if (MapUtils.isEmpty(stepGroupCache.getStepGroups(processName))) {
            Map<String, StepGroup<K, V>> stepGroups = new LinkedHashMap<>();
            workflowResource.getAllStepsByProcessName(processName).forEach(
                stepTemplate -> {
                    StepGroup<K, V> step = stepGroups.computeIfAbsent(stepTemplate.getStepGroup(), k -> new DefaultStepGroup<K,V>());
                    step.addStep((Step<K, V>)stepBuilder.build(stepTemplate));
                    step.setStepGroupName(stepTemplate.getStepGroup());
            });
            stepGroups.forEach((stepGroupName, stepGroup) -> stepGroupCache.addStepGroup(processName, stepGroup));
        }

        return stepGroupCache.getStepGroups(processName);
    }

}
