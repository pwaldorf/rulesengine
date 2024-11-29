package com.pw.workflowengine.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import com.pw.workflowengine.stepbuilder.StepBuilder;
import com.pw.workflowengine.stepengine.WorkflowResource;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepGroup;

@Component
public class StepRepository {

    private final WorkflowResource workflowResource;
    private final StepBuilder stepBuilder;

    public StepRepository(WorkflowResource workflowResource, StepBuilder stepBuilder) {
        this.workflowResource = workflowResource;
        this.stepBuilder = stepBuilder;
    }

    public Map<String, StepGroup> getStepGroupsByProcess(String processName) {

        if (MapUtils.isEmpty(WorkflowCache.getStepGroups(processName))) {
            @SuppressWarnings("rawtypes")
            Map<String, Action> actions = new LinkedHashMap<>();
            workflowResource.getStepsByProcessName(processName).forEach(step -> {
                @SuppressWarnings("rawtypes")
                Action action = stepBuilder.build(step);
                actions.put(step.getStepName(), action);
            });
            actions.forEach((stepName, action) -> ActionCache.addAction(stepName, action));

            Map<String, StepGroup> stepGroups = new LinkedHashMap<>();
            workflowResource.getStepGroupsByProcessName(processName).forEach(
                stepGroup -> {
                    stepGroup.getStepTemplates().forEach(template -> {
                        template.setStepRuleAction(ActionCache.getAction(template.getStepRuleName()));
                        if (template.getStepPassName() != null && !template.getStepPassName().isEmpty()) {
                            template.setStepPassAction(ActionCache.getAction(template.getStepPassName()));
                        }
                        if (template.getStepFailName() != null && !template.getStepFailName().isEmpty()) {
                            template.setStepFailAction(ActionCache.getAction(template.getStepFailName()));
                        }
                        if (template.getStepAlwaysName() != null && !template.getStepAlwaysName().isEmpty()) {
                            template.setStepAlwaysAction(ActionCache.getAction(template.getStepAlwaysName()));
                        }
                    });
                    stepGroups.put(stepGroup.getStepGroupName(), stepGroup);
            });
            stepGroups.forEach((stepGroupName, stepGroup) -> WorkflowCache.addStepGroup(processName, stepGroup));

        }

        return WorkflowCache.getStepGroups(processName);
    }

    @SuppressWarnings("rawtypes")
    public Action getAction(String actionName) {
        return ActionCache.getAction(actionName);
    }

}
