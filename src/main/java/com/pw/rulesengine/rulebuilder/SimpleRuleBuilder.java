package com.pw.rulesengine.rulebuilder;

import org.springframework.stereotype.Component;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.rule.RuleTemplate;
import com.pw.rulesengine.rule.impl.SimpleRule;
import com.pw.rulesengine.ruleengine.statement.SimpleStatement;

@Component
public class SimpleRuleBuilder<T, U> implements RuleBuilder<Rule<T, U>> {

    private final RuleFactory<T, U> ruleFactory;

    public SimpleRuleBuilder(RuleFactory<T, U> ruleFactory) {
        this.ruleFactory = ruleFactory;
    }

    @Override
    public Rule<T, U> build(RuleTemplate ruleTemplate) {

        Condition<T> condition = ruleFactory.getConditionBuilder(ruleTemplate.getConditionType()).build(ruleTemplate);
        Action<U> action = ruleFactory.getActionBuilder(ruleTemplate.getPassActionType()).build("PASS", ruleTemplate);

        return new SimpleRule<T, U>(condition, action, new SimpleStatement());
    }
}
