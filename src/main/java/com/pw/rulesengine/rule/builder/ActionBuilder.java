package com.pw.rulesengine.rule.builder;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.ruleengine.RuleTemplate;

public interface ActionBuilder<T> {
    String getType();
    Action<T> build(RuleTemplate ruletemplate);
}
