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
    Map<String, Object> evaluateContext;
    String evaluateExpression;

    Map<String, Object> passContext;
    String passExpression;

    Map<String, Object> failContext;
    String failExpression;

    Map<String, Object> alwaysContext;
    String alwaysExpression;
}
