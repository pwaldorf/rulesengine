package com.pw.rulesengine.rulebuilder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.RuleTemplate;
import com.pw.rulesengine.rule.impl.JavaAction;
import com.pw.rulesengine.rulebuilder.ActionBuilder;

@Service
public class JavaActionBuilder<U> implements ActionBuilder<U> {

    private final ApplicationContext applicationContext;

    public JavaActionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public Action<U> build(String type, RuleTemplate ruleTemplate) {
        try {
            ClassLoader classLoader = applicationContext.getClassLoader();

            if (type.toUpperCase().equals("PASS")) {
                Class<? extends JavaAction<U>> ruleClass
                        = (Class<? extends JavaAction<U>>)classLoader.loadClass(ruleTemplate.getJavaClassName().getPassClassName());
                return ruleClass.getDeclaredConstructor().newInstance();
            }

            if (type.toUpperCase().equals("FAIL")) {
                Class<? extends JavaAction<U>> ruleClass
                        = (Class<? extends JavaAction<U>>)classLoader.loadClass(ruleTemplate.getJavaClassName().getFailClassName());
                return ruleClass.getDeclaredConstructor().newInstance();
            }

            Class<? extends JavaAction<U>> ruleClass
                        = (Class<? extends JavaAction<U>>)classLoader.loadClass(ruleTemplate.getJavaClassName().getAlwaysClassName());
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
