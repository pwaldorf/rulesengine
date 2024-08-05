package com.pw.rulesengine.rulebuilder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.RuleTemplate;
import com.pw.rulesengine.rule.impl.SpringCondition;
import com.pw.rulesengine.rulebuilder.ConditionBuilder;

@Service
public class SpringConditionBuilder<T> implements ConditionBuilder<T> {

    private final ApplicationContext applicationContext;

    public SpringConditionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Condition<T> build(RuleTemplate ruleTemplate) {
        return new SpringCondition<T>(applicationContext, ruleTemplate.getSpringMethod());
    }

    @Override
    public String getType() {
        return "SPRING";
    }

}
