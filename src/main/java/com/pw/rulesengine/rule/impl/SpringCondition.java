package com.pw.rulesengine.rule.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.pw.rulesengine.models.DefaultSpringMethod;
import com.pw.rulesengine.rule.Condition;

public class SpringCondition<T> implements Condition<T> {

    private final ApplicationContext applicationContext;
    private final DefaultSpringMethod springMethod;

    public SpringCondition(ApplicationContext applicationContext, DefaultSpringMethod springMethod) {
        this.applicationContext = applicationContext;
        this.springMethod = springMethod;
    }

    @Override
    public String getType() {
        return "SPRING";
    }

    @Override
    public boolean evaluate(T o) {
        if (StringUtils.isBlank(springMethod.getEvaluateBeanName()) ||
        StringUtils.isBlank(springMethod.getEvaluateMethodName())) {
            return true;
        }
        try {
            Object bean = applicationContext.getBean(springMethod.getEvaluateBeanName());
            Method method = bean.getClass().getMethod(springMethod.getEvaluateMethodName(), Map.class);
            return (Boolean) method.invoke(bean, o);
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating rule with bean method: " + springMethod.getEvaluateMethodName(), e);
        }
    }

}
