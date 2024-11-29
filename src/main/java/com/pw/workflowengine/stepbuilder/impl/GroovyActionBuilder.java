package com.pw.workflowengine.stepbuilder.impl;

import com.pw.workflowengine.workflow.impl.GroovyAction;
import com.pw.workflowengine.stepbuilder.ActionBuilder;
import com.pw.workflowengine.stepbuilder.model.GroovyExpression;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Step;

public class GroovyActionBuilder  implements ActionBuilder<String, Object> {

    @Override
    public String getType() {
        return "GROOVY";
    }

    @Override
    public Action<String, Object> build(Step step) {
        GroovyExpression groovyExpression = new GroovyExpression();
        groovyExpression.setExpression(step.getStepObject());
        groovyExpression.setGroovyMethod(step.getStepContext().get(0));
        return new GroovyAction(groovyExpression);
    }
}
