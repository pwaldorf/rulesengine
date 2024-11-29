package com.pw.workflowengine.workflow.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import com.pw.workflowengine.stepbuilder.model.SpringBean;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepGroup;

public class SpringAction implements Action<String, Object>{

    private String actionName;

    private final ApplicationContext applicationContext;
    private final SpringBean springBean;

    public SpringAction(ApplicationContext applicationContext, SpringBean springBean) {
        this.applicationContext = applicationContext;
        this.springBean = springBean;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String getType() {
        return "SPRING";
    }

    @Override
    public boolean execute(Map<String, Object> context, Map<String, StepGroup> stepGroups) {
        if (StringUtils.isBlank(springBean.getMethodName()) ||
        StringUtils.isBlank(springBean.getBeanName())) {
            return true;
        }
        return invokeMethod(springBean.getBeanName(), springBean.getMethodName(), context);
    }

    private boolean invokeMethod(String beanName, String methodName, Map<String, Object> context) {
        try {
            Object bean = applicationContext.getBean(beanName);
            Method method = bean.getClass().getMethod(methodName, Map.class);
            return (boolean) method.invoke(bean, context);
        } catch (Exception e) {
            throw new RuntimeException("Error invokinging rule with bean method: " + methodName, e);
        }
    }
}
