package com.pw.rulesengine.rulebuilder;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.RuleTemplate;

public interface ActionBuilder<U> {
    String getType();
    Action<U> build(String Type, RuleTemplate ruleTemplate);
}
