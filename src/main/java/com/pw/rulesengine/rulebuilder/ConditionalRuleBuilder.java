package com.pw.rulesengine.rulebuilder;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.rule.RuleTemplate;
import com.pw.rulesengine.rule.impl.ConditionalRule;
import com.pw.rulesengine.ruleengine.statement.IfElseStatement;

public class ConditionalRuleBuilder<T, U> implements RuleBuilder<Rule<T, U>> {

    private final RuleFactory<T, U> ruleFactory;

    public ConditionalRuleBuilder(RuleFactory<T, U> ruleFactory) {
        this.ruleFactory = ruleFactory;
    }

    @Override
    public Rule<T, U> build(RuleTemplate ruleTemplate) {

        Condition<T> condition = ruleFactory.getConditionBuilder(ruleTemplate.getConditionType()).build(ruleTemplate);
        Action<U> passAction = ruleFactory.getActionBuilder(ruleTemplate.getPassActionType()).build("PASS", ruleTemplate);
        Action<U> failAction = ruleFactory.getActionBuilder(ruleTemplate.getFailActionType()).build("FAIL", ruleTemplate);

        return new ConditionalRule<T, U>(condition, passAction, failAction, new IfElseStatement());
    }

}
