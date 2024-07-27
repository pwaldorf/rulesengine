package com.pw.rulesengine.models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefaultExpression {
    String evaluateExpression;
    String passExpression;
    String failExpression;
    String alwaysExpression;
    Map<String, String> context;
}
