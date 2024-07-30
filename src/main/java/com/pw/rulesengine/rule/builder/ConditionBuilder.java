package com.pw.rulesengine.rule.builder;

import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.ruleengine.RuleTemplate;

public interface ConditionBuilder<T> extends Builder<Condition<T>, RuleTemplate> {
    String getType();
    Condition<T> build(RuleTemplate ruleTemplate);
}
