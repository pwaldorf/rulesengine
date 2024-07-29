package com.pw.rulesengine.rule.builder;

import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.ruleengine.RuleTemplate;

public interface RuleBuilder {
    @SuppressWarnings("rawtypes")
    Rule build(RuleTemplate ruleTemplate);
}
