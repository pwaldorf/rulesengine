package com.pw.rulesengine.rule.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.pw.rulesengine.rule.Rule;

public class SpringRule<T, U> implements Rule<T, U> {

    private final ApplicationContext applicationContext;
    private final String evaluateBeanName;
    private final String evaluateMethodName;
    private final String executeBeanName;
    private final String executeMethodName;

    public SpringRule(ApplicationContext applicationContext,
                String evaluateBeanName,
                String evaluateMethodName,
                String executeBeanName,
                String executeMethodName) {
        this.applicationContext = applicationContext;
        this.evaluateBeanName = evaluateBeanName;
        this.evaluateMethodName = evaluateMethodName;
        this.executeBeanName = executeBeanName;
        this.executeMethodName = executeMethodName;
    }

    @Override
    public String getType() {
        return "SPRING";
    }

    @Override
    public boolean evaluate(T t) {
        try {
            Object bean = applicationContext.getBean(evaluateBeanName);
            Method method = bean.getClass().getMethod(evaluateMethodName, Map.class);
            return (Boolean) method.invoke(bean, t);
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating rule with bean method: " + evaluateMethodName, e);
        }
    }

    @Override
    public void execute(U u) {
        try {
            Object bean = applicationContext.getBean(executeBeanName);
            Method method = bean.getClass().getMethod(executeMethodName, Map.class);
            method.invoke(bean, u);
        } catch (Exception e) {
            throw new RuntimeException("Error invokinging rule with bean method: " + executeMethodName, e);
        }
    }

}
