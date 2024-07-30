package com.pw.rulesengine.models;

import java.util.Map;

import com.pw.rulesengine.rule.Expression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefaultExpression implements Expression {
    String evaluateExpression;
    String passExpression;
    String failExpression;
    String alwaysExpression;
    Map<String, Object> context;
}
