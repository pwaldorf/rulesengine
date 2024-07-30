package com.pw.rulesengine.rule.builder;

import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.ruleengine.RuleTemplate;

public interface RuleBuilder<T, U> extends Builder<Rule<T, U>, RuleTemplate>{
    Rule<T, U> build(RuleTemplate ruleTemplate);
}
