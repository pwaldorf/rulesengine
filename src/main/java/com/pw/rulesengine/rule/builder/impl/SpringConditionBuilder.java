package com.pw.rulesengine.rule.builder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.builder.ConditionBuilder;
import com.pw.rulesengine.rule.impl.SpringCondition;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@Service
public class SpringConditionBuilder<T> implements ConditionBuilder<T> {

    private final ApplicationContext applicationContext;

    public SpringConditionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Condition<T> build(RuleTemplate ruleTemplate) {
        return new SpringCondition(applicationContext, ruleTemplate.getSpringMethod());
    }

    @Override
    public String getType() {
        return "SPRING";
    }

}
