package com.pw.workflowengine.stepengine.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.pw.workflowengine.stepengine.StepEngine;
import com.pw.workflowengine.stepengine.StepRepository;
import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.StepGroup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SimpleWorkflowEngine<K, V> implements StepEngine<K, V> {

    private final StepRepository<K, V> stepRepository;

    public SimpleWorkflowEngine(StepRepository<K, V> stepRepository) {
        this.stepRepository = stepRepository;
    }

    @Override
    public void run(String processName, Map<K, V> context) {
        Map<String, StepGroup<K, V>> stepGroups = stepRepository.getByProcess(processName);
        if (stepGroups != null) {
            StepGroup<K, V> stepGroup = stepGroups.get("startGroup".toUpperCase());
            List<Step<K, V>> steps = stepGroup.getSteps();

            Iterator<Step<K, V>> iterator = steps.iterator();

            while (iterator.hasNext()) {
                Step<K, V> step = iterator.next();
                boolean result = false;
                if (step.getCondition().evaluate(context)) {
                    step.getPassAction().execute(context, stepGroups);
                    result = true;
                } else {
                      step.getFailAction().execute(context, stepGroups);
                }
                step.getAlwaysAction().execute(context, stepGroups);

                if ((result && step.isBreakOnPass()) || (!result && step.isBreakOnFail())) {
                    break;
                }
            }
        }
    }

}
