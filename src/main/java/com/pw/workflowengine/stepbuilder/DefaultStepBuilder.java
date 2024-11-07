package com.pw.workflowengine.stepbuilder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Condition;
import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.StepTemplate;
import com.pw.workflowengine.workflow.impl.DefaultStep;

@Component
public class DefaultStepBuilder implements StepBuilder<String, Object> {

    private final StepFactory<String, Object> ruleFactory;

    public DefaultStepBuilder(StepFactory<String, Object> ruleFactory) {
        this.ruleFactory = ruleFactory;
    }

    @Override
    public Step<String, Object> build(StepTemplate<String, Object> ruleTemplate) {

        Condition<String, Object> condition = ruleFactory.getConditionBuilder(ruleTemplate.getStepRuleType()).build(ruleTemplate);

        Action<String, Object> passAction = StringUtils.isNotBlank(ruleTemplate.getStepPassType())
                    ? ruleFactory.getActionBuilder(ruleTemplate.getStepPassType()).build("PASS", ruleTemplate)
                    : (Action<String, Object>) new DefaultAction<String, Object>();
        Action<String, Object> failAction = StringUtils.isNotBlank(ruleTemplate.getStepFailType())
                    ? ruleFactory.getActionBuilder(ruleTemplate.getStepFailType()).build("FAIL", ruleTemplate)
                    : (Action<String, Object>) new DefaultAction<String, Object>();
        Action<String, Object> alwaysAction = StringUtils.isNotBlank(ruleTemplate.getStepAlwaysType())
                    ? ruleFactory.getActionBuilder(ruleTemplate.getStepAlwaysType()).build("ALWAYS", ruleTemplate)
                    : (Action<String, Object>) new DefaultAction<String, Object>();

        return new DefaultStep<String, Object>(condition, passAction, failAction, alwaysAction,
                                        ruleTemplate.isBreakOnPass(), ruleTemplate.isBreakOnFail());
        // return new DefaultStep(condition, simpleStatement, passAction, failAction, alwaysAction,
        //                                 ruleTemplate.isBreakOnPass(), ruleTemplate.isBreakOnFail());
    }
}
