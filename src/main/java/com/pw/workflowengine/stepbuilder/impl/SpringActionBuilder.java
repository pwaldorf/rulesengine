package com.pw.workflowengine.stepbuilder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ActionBuilder;
import com.pw.workflowengine.stepbuilder.model.SpringBean;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepTemplate;
import com.pw.workflowengine.workflow.impl.SpringAction;

@Service
public class SpringActionBuilder implements ActionBuilder<String, Object> {
    private final ApplicationContext applicationContext;

    public SpringActionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Action<String, Object> build(String type, StepTemplate<String, Object> ruleTemplate) {

        if (type.toUpperCase().equals("PASS")) {
            SpringBean springBean = new SpringBean();
            springBean.setBeanName(ruleTemplate.getSpringMethod().getPassBeanName());
            springBean.setMethodName(ruleTemplate.getSpringMethod().getPassMethodName());
            return new SpringAction(applicationContext, springBean);
        }

        if (type.toUpperCase().equals("FAIL")) {
            SpringBean springBean = new SpringBean();
            springBean.setBeanName(ruleTemplate.getSpringMethod().getFailBeanName());
            springBean.setMethodName(ruleTemplate.getSpringMethod().getFailMethodName());
            return new SpringAction(applicationContext, springBean);
        }

        SpringBean springBean = new SpringBean();
        springBean.setBeanName(ruleTemplate.getSpringMethod().getAlwaysBeanName());
        springBean.setMethodName(ruleTemplate.getSpringMethod().getAlwaysMethodName());
        return new SpringAction(applicationContext, springBean);
    }

    @Override
    public String getType() {
        return "SPRING";
    }
}
