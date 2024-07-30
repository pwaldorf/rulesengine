package com.pw.rulesengine.rule.builder.impl;

import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.builder.ConditionBuilder;
import com.pw.rulesengine.rule.impl.MvelCondition;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@Service
public class MvelConditionBuilder<T> implements ConditionBuilder<T> {

    public Condition<T> build(RuleTemplate ruleTemplate) {
        return new MvelCondition<T>(ruleTemplate.getExpression().getEvaluateExpression(), ruleTemplate.getExpression().getContext());
    }

    @Override
    public String getType() {
        return "MVEL";
    }
}
