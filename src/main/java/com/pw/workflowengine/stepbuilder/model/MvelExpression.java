package com.pw.workflowengine.stepbuilder.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MvelExpression{
    String expression;
    Map<String, String> context;
}
