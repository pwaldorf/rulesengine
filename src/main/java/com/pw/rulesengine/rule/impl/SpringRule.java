package com.pw.rulesengine.rule.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.pw.rulesengine.models.DefaultSpringMethod;
import com.pw.rulesengine.rule.Rule;

public class SpringRule<T, U> implements Rule<T, U> {

    private final ApplicationContext applicationContext;
    private final DefaultSpringMethod springMethod;

    public SpringRule(ApplicationContext applicationContext, DefaultSpringMethod springMethod) {
        this.applicationContext = applicationContext;
        this.springMethod = springMethod;
    }

    @Override
    public String getType() {
        return "SPRING";
    }

    @Override
    public boolean evaluate(T t) {
        if (StringUtils.isBlank(springMethod.getEvaluateBeanName()) ||
        StringUtils.isBlank(springMethod.getEvaluateMethodName())) {
            return true;
        }
        try {
            Object bean = applicationContext.getBean(springMethod.getEvaluateBeanName());
            Method method = bean.getClass().getMethod(springMethod.getEvaluateMethodName(), Map.class);
            return (Boolean) method.invoke(bean, t);
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating rule with bean method: " + springMethod.getEvaluateMethodName(), e);
        }
    }

    @Override
    public void pass(U u) {
        if (StringUtils.isBlank(springMethod.getPassMethodName()) ||
        StringUtils.isBlank(springMethod.getPassBeanName())) {
            return;
        }
        invokeMethod(springMethod.getPassBeanName(), springMethod.getPassMethodName(), u);
    }

    @Override
    public void fail(U u) {
        if (StringUtils.isBlank(springMethod.getFailMethodName()) ||
        StringUtils.isBlank(springMethod.getFailBeanName())) {
            return;
        }
        invokeMethod(springMethod.getFailBeanName(), springMethod.getFailMethodName(), u);
    }

    @Override
    public void always(U u) {
        if (StringUtils.isBlank(springMethod.getAlwaysMethodName()) ||
        StringUtils.isBlank(springMethod.getAlwaysBeanName())) {
            return;
        }
        invokeMethod(springMethod.getAlwaysBeanName(), springMethod.getAlwaysMethodName(), u);
    }

    private void invokeMethod(String beanName, String methodName, U u) {
        try {
            Object bean = applicationContext.getBean(beanName);
            Method method = bean.getClass().getMethod(methodName, Map.class);
            method.invoke(bean, u);
        } catch (Exception e) {
            throw new RuntimeException("Error invokinging rule with bean method: " + methodName, e);
        }
    }

}
