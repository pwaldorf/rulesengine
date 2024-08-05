package com.pw.rulesengine.rulebuilder.impl;

import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.RuleTemplate;
import com.pw.rulesengine.rule.impl.MvelCondition;
import com.pw.rulesengine.rulebuilder.ConditionBuilder;

@Service
public class MvelConditionBuilder<T> implements ConditionBuilder<T> {

    public Condition<T> build(RuleTemplate ruleTemplate) {
        return new MvelCondition<T>(ruleTemplate.getExpression().getEvaluateExpression(), ruleTemplate.getExpression().getEvaluateContext());
    }

    @Override
    public String getType() {
        return "MVEL";
    }
}
