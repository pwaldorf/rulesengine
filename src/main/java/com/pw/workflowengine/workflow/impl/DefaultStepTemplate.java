package com.pw.workflowengine.workflow.impl;

import com.pw.workflowengine.workflow.Expression;
import com.pw.workflowengine.workflow.JavaClassName;
import com.pw.workflowengine.workflow.StepTemplate;
import com.pw.workflowengine.workflow.SpringMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefaultStepTemplate implements StepTemplate<String, Object> {
    private String processName;
    private String stepGroup;
    private Integer order;

    private String stepRuleType;
    private String stepPassType;
    private String stepFailType;
    private String stepAlwaysType;

    private boolean breakOnPass;
    private boolean breakOnFail;

    private String description;

    // MVEL Expressions
    private Expression<String, Object> expression;

    // JAVA Classes
    private JavaClassName javaClassName;

    // Spring Beans
    private SpringMethod springMethod;

}
