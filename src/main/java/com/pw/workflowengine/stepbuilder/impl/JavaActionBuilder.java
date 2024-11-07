package com.pw.workflowengine.stepbuilder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ActionBuilder;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.StepTemplate;
import com.pw.workflowengine.workflow.impl.JavaAction;

@Service
public class JavaActionBuilder implements ActionBuilder<String, Object> {

    private final ApplicationContext applicationContext;

    public JavaActionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings({ "unchecked", "null" })
    @Override
    public Action<String, Object> build(String type, StepTemplate<String, Object> ruleTemplate) {
        try {
            ClassLoader classLoader = applicationContext.getClassLoader();

            if (type.toUpperCase().equals("PASS")) {
                Class<? extends JavaAction<String, Object>> ruleClass
                        = (Class<? extends JavaAction<String, Object>>)classLoader.loadClass(ruleTemplate.getJavaClassName().getPassClassName());
                return ruleClass.getDeclaredConstructor().newInstance();
            }

            if (type.toUpperCase().equals("FAIL")) {
                Class<? extends JavaAction<String, Object>> ruleClass
                        = (Class<? extends JavaAction<String, Object>>)classLoader.loadClass(ruleTemplate.getJavaClassName().getFailClassName());
                return ruleClass.getDeclaredConstructor().newInstance();
            }

            Class<? extends JavaAction<String, Object>> ruleClass
                        = (Class<? extends JavaAction<String, Object>>)classLoader.loadClass(ruleTemplate.getJavaClassName().getAlwaysClassName());
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
