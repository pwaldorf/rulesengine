package com.pw.rulesengine.rule.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.SpringMethod;

public class SpringAction<U> implements Action<U>{

    private final ApplicationContext applicationContext;
    private final SpringMethod springMethod;

    public SpringAction(ApplicationContext applicationContext, SpringMethod springMethod) {
        this.applicationContext = applicationContext;
        this.springMethod = springMethod;
    }

    @Override
    public String getType() {
        return "SPRING";
    }

    @Override
    public void execute(U u) {
        if (StringUtils.isBlank(springMethod.getPassMethodName()) ||
        StringUtils.isBlank(springMethod.getPassBeanName())) {
            return;
        }
        invokeMethod(springMethod.getPassBeanName(), springMethod.getPassMethodName(), u);
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
