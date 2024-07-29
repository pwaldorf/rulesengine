package com.pw.rulesengine.rule.builder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.builder.ActionBuilder;
import com.pw.rulesengine.rule.impl.JavaAction;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@Service
public class JavaActionBuilder<T> implements ActionBuilder<T> {

    private final ApplicationContext applicationContext;

    public JavaActionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Action build(RuleTemplate ruleTemplate) {
        try {
            ClassLoader classLoader = applicationContext.getClassLoader();
            Class<? extends JavaAction> ruleClass = (Class<? extends JavaAction>)classLoader.loadClass(ruleTemplate.getActionClassName());
            return ruleClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create rule instance", e);
        }
    }

    @Override
    public String getType() {
        return "JAVA";
    }

}
