package com.pw.workflowengine.workflow.impl;

import java.util.List;
import java.util.Map;

import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepGroup;
import com.pw.workflowengine.workflow.StepTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepGroupAction<K, V> implements Action<K, V> {

    private String actionName;

    private final String stepGroupName;

    public StepGroupAction(String stepGroupName) {
        this.stepGroupName = stepGroupName;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public boolean execute(Map<K, V> context, Map<String, StepGroup> stepGroups) {
        log.debug("Executing step group: {} : {} ", stepGroupName, context);
        // StepGroup stepGroup = stepGroups.get(stepGroupName);
        List<StepTemplate> stepTemplates = stepGroups.get(stepGroupName).getStepTemplates();

        // Process each step template
        for (int i = 0, size = stepTemplates.size(); i < size; i++) {
            StepTemplate stepTemplate = stepTemplates.get(i);

            // Execute the step and determine result
            boolean result = executeAction(stepTemplate.getStepRuleAction(), context, stepGroups);
            if (result) {
                executeOptionalAction(stepTemplate.getStepPassAction(), context, stepGroups);
            } else {
                executeOptionalAction(stepTemplate.getStepFailAction(), context, stepGroups);
            }

            // Always execute stepAlwaysAction if defined
            executeOptionalAction(stepTemplate.getStepAlwaysAction(), context, stepGroups);

            // Break on conditions
            if ((result && stepTemplate.isBreakOnPass()) || (!result && stepTemplate.isBreakOnFail())) {
                log.debug("Breaking execution at step: {} due to break condition.", stepTemplate.getStepRuleName());
                break;
            }
        }

        return true;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private boolean executeAction(Action action, Map<K, V> context, Map<String, StepGroup> stepGroups) {
        return action != null && action.execute(context, stepGroups);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void executeOptionalAction(Action action, Map<K, V> context, Map<String, StepGroup> stepGroups) {
        if (action != null) {
            action.execute(context, stepGroups);
        }
    }

    @Override
    public String getType() {
        return "STEPGROUP";
    }

    public String getStepGroup() {
        return stepGroupName;
    }

}
