package com.pw.rulesengine.rulebuilder;

import com.pw.rulesengine.rule.RuleTemplate;

public interface RuleBuilder<T> {
    T build(RuleTemplate ruleTemplate);
}
