package com.pw.workflowengine.workflow.impl;

import java.util.Map;

import com.pw.workflowengine.workflow.Expression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefaultExpression implements Expression<String, Object> {
    Map<String, Object> evaluateContext;
    String evaluateExpression;

    Map<String, Object> passContext;
    String passExpression;

    Map<String, Object> failContext;
    String failExpression;

    Map<String, Object> alwaysContext;
    String alwaysExpression;

}
