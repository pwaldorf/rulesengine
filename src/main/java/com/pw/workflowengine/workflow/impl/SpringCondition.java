package com.pw.workflowengine.workflow.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.pw.workflowengine.workflow.Condition;
import com.pw.workflowengine.workflow.SpringMethod;

public class SpringCondition<K, V> implements Condition<K, V> {

    private final ApplicationContext applicationContext;
    private final SpringMethod springMethod;

    public SpringCondition(ApplicationContext applicationContext, SpringMethod springMethod) {
        this.applicationContext = applicationContext;
        this.springMethod = springMethod;
    }

    @Override
    public String getType() {
        return "SPRING";
    }

    @Override
    public boolean evaluate(Map<K, V> context) {
        if (StringUtils.isBlank(springMethod.getEvaluateBeanName()) ||
        StringUtils.isBlank(springMethod.getEvaluateMethodName())) {
            return true;
        }
        try {
            Object bean = applicationContext.getBean(springMethod.getEvaluateBeanName());
            Method method = bean.getClass().getMethod(springMethod.getEvaluateMethodName(), Map.class);
            return (Boolean) method.invoke(bean, context);
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating rule with bean method: " + springMethod.getEvaluateMethodName(), e);
        }
    }

}
