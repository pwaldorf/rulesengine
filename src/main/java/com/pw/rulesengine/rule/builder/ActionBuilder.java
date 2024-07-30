package com.pw.rulesengine.rule.builder;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.ruleengine.RuleTemplate;

public interface ActionBuilder<U> extends Builder<Action<U>, RuleTemplate> {
    String getType();
    Action<U> build(RuleTemplate ruletemplate);
}
