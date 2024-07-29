package com.pw.rulesengine.rule.builder;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.rule.impl.DefaultRule;
import com.pw.rulesengine.ruleengine.RuleTemplate;

public class DefaultRuleBuilder implements RuleBuilder{

    private final RuleFactory ruleFactory;

    public DefaultRuleBuilder(RuleFactory ruleFactory) {
        this.ruleFactory = ruleFactory;
    }

    @Override
    public Rule build(RuleTemplate ruleTemplate) {

        Condition condition = ruleFactory.getConditionBuilder(ruleTemplate.getConditionType()).build(ruleTemplate);
        Action action = ruleFactory.getActionBuilder(ruleTemplate.getActionType()).build(ruleTemplate);

        return new DefaultRule(condition, action);

    }

}
