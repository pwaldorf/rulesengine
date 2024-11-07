package com.pw.workflowengine.workflow.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.StepGroup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepGroupAction<K, V> implements Action<K, V> {

    private final String stepGroupName;

    public StepGroupAction(String stepGroupName) {
        this.stepGroupName = stepGroupName;
    }

    @Override
    public void execute(Map<K, V> context, Map<String, StepGroup<K, V>> stepGroups) {
      log.debug("Executing step group: {} : {} ", stepGroupName, context);
      StepGroup<K, V> stepGroup = stepGroups.get(stepGroupName.toUpperCase());
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


    @Override
    public String getType() {
        return "STEPGROUP";
    }

    public String getStepGroup() {
        return stepGroupName;
    }

}
