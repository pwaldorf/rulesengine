package com.pw.rulesengine.rule.builder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.builder.ConditionBuilder;
import com.pw.rulesengine.rule.impl.JavaCondition;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@Service
public class JavaConditionBuilder<T> implements ConditionBuilder<T> {

    private final ApplicationContext applicationContext;

    public JavaConditionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Condition build(RuleTemplate ruleTemplate) {
        try {
            ClassLoader classLoader = applicationContext.getClassLoader();
            Class<? extends JavaCondition> ruleClass = (Class<? extends JavaCondition>)classLoader.loadClass(ruleTemplate.getConditionClassName());
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
