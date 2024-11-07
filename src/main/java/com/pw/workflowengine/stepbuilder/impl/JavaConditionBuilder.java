package com.pw.workflowengine.stepbuilder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepbuilder.ConditionBuilder;
import com.pw.workflowengine.workflow.Condition;
import com.pw.workflowengine.workflow.StepTemplate;
import com.pw.workflowengine.workflow.impl.JavaCondition;

@Service
public class JavaConditionBuilder implements ConditionBuilder<String, Object> {

    private final ApplicationContext applicationContext;

    public JavaConditionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Condition<String, Object> build(StepTemplate<String, Object> ruleTemplate) {
        try {
            ClassLoader classLoader = applicationContext.getClassLoader();

            @SuppressWarnings({ "null", "unchecked" })
            Class<? extends JavaCondition<String, Object>> ruleClass
                    = (Class<? extends JavaCondition<String, Object>>)classLoader.loadClass(ruleTemplate.getJavaClassName().getConditionClassName());
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
