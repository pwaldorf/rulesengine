package com.pw.rulesengine.rulebuilder.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MvelExpression{
    String expression;
    Map<String, Object> context;
}
