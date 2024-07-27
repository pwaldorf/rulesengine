package com.pw.rulesengine.rule.builder.impl;

import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.rule.builder.RuleBuilder;
import com.pw.rulesengine.rule.impl.SpringRule;
import com.pw.rulesengine.ruleengine.RuleTemplate;

import org.springframework.context.ApplicationContext;

@Service
public class SpringRuleBuilder implements RuleBuilder {

    private final ApplicationContext applicationContext;

    public SpringRuleBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Rule build(RuleTemplate ruleTemplate) {
        return new SpringRule(applicationContext,
                ruleTemplate.getSpringMethod());
    }

    @Override
    public String getType() {
        return "SPRING";
    }
}
