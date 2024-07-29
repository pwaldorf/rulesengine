package com.pw.rulesengine.rule.builder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.builder.ActionBuilder;
import com.pw.rulesengine.rule.impl.SpringAction;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@Service
public class SpringActionBuilder<T> implements ActionBuilder<T> {
    private final ApplicationContext applicationContext;

    public SpringActionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Action<T> build(RuleTemplate ruleTemplate) {
        return new SpringAction(applicationContext, ruleTemplate.getSpringMethod());
    }

    @Override
    public String getType() {
        return "SPRING";
    }
}
