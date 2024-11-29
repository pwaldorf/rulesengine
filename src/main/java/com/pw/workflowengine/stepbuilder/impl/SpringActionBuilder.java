package com.pw.workflowengine.stepbuilder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ActionBuilder;
import com.pw.workflowengine.stepbuilder.model.SpringBean;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Step;
import com.pw.workflowengine.workflow.impl.SpringAction;

@Service
public class SpringActionBuilder implements ActionBuilder<String, Object> {
    private final ApplicationContext applicationContext;

    public SpringActionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Action<String, Object> build(Step step) {

        SpringBean springBean = new SpringBean();
        String[] parts = step.getStepObject().split("\\.", 2);
        springBean.setBeanName(parts[0]);
        springBean.setMethodName(parts[1]);
        return new SpringAction(applicationContext, springBean);
    }

    @Override
    public String getType() {
        return "SPRING";
    }
}
