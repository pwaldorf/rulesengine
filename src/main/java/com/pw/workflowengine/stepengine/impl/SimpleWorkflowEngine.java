package com.pw.workflowengine.stepengine.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pw.workflowengine.stepengine.StepEngine;
import com.pw.workflowengine.util.StepRepository;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepGroup;
import com.pw.workflowengine.workflow.StepTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SimpleWorkflowEngine<K, V> implements StepEngine<K, V> {

    private final StepRepository stepRepository;

    public SimpleWorkflowEngine(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    @Override
    public void run(String processName, Map<K, V> context) {
        Map<String, StepGroup> stepGroups = stepRepository.getStepGroupsByProcess(processName);

        List<StepTemplate> stepTemplates = stepGroups.get("startGroup").getStepTemplates();

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
}
