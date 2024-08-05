package com.pw.rulesengine.rulebuilder;

import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.RuleTemplate;

public interface ConditionBuilder<T> {
    String getType();
    Condition<T> build(RuleTemplate ruleTemplate);
}
