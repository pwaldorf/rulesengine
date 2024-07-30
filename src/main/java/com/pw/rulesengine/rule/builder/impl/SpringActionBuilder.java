package com.pw.rulesengine.rule.builder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.builder.ActionBuilder;
import com.pw.rulesengine.rule.impl.SpringAction;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@Service
public class SpringActionBuilder<U> implements ActionBuilder<U> {
    private final ApplicationContext applicationContext;

    public SpringActionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Action<U> build(RuleTemplate ruleTemplate) {
        return new SpringAction<U>(applicationContext, ruleTemplate.getSpringMethod());
    }

    @Override
    public String getType() {
        return "SPRING";
    }
}
