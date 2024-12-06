package com.pw.workflowengine.workflow.impl;

import java.util.Map;

import com.pw.workflowengine.stepbuilder.model.GroovyExpression;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.GroovyScript;
import com.pw.workflowengine.workflow.StepGroup;

import groovy.lang.GroovyClassLoader;

public class GroovyAction implements Action<String, Object> {

    private GroovyScript script;

    @SuppressWarnings({ "unchecked"})
    public GroovyAction(GroovyExpression groovyExpression) {

        try (var gcl = new GroovyClassLoader();) {
            script = (GroovyScript) gcl.parseClass(groovyExpression.getExpression()).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean execute(Map<String, Object> context, Map<String, StepGroup> stepGroups) {
        script.execute(context);
        return true;
    }

    @Override
    public String getType() {
        return "GROOVY";
    }
}
