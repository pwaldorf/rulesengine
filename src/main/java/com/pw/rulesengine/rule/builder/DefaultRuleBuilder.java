package com.pw.rulesengine.rule.builder;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.rule.impl.DefaultRule;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@SuppressWarnings("rawtypes")
public class DefaultRuleBuilder<T, U> implements RuleBuilder<Rule, RuleTemplate> {

    private final RuleFactory<T, U> ruleFactory;

    public DefaultRuleBuilder(RuleFactory<T, U> ruleFactory) {
        this.ruleFactory = ruleFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Rule build(RuleTemplate ruleTemplate) {

        Condition<T> condition = ruleFactory.getConditionBuilder(ruleTemplate.getConditionType()).build(ruleTemplate);
        Action<U> action = ruleFactory.getActionBuilder(ruleTemplate.getActionType()).build(ruleTemplate);

        return new DefaultRule<T, U>(condition, action);

    }

}
