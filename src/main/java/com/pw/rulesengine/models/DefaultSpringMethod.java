package com.pw.rulesengine.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefaultSpringMethod {
    private String evaluateBeanName;
    private String evaluateMethodName;
    private String passBeanName;
    private String passMethodName;
    private String failBeanName;
    private String failMethodName;
    private String alwaysBeanName;
    private String alwaysMethodName;
}
