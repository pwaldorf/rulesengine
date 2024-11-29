package com.pw.workflowengine.stepbuilder.impl;

import static org.joor.Reflect.onClass;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ActionBuilder;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Step;

@Service
public class JavaActionBuilder implements ActionBuilder<String, Object> {

    private final ApplicationContext applicationContext;

    public JavaActionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Action<String, Object> build(Step step) {

        return onClass(step.getStepObject(), applicationContext.getClassLoader()).create().get();

    }

    @Override
    public String getType() {
        return "JAVA";
    }

}
