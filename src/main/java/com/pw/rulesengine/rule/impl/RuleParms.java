package com.pw.rulesengine.rule.impl;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleParms {
    private String evaluateBeanName;
    private String evaluateMethodName;
    private String executeBeanName;
    private String executeMethodName;

    private Map<String, Object> context;
}
