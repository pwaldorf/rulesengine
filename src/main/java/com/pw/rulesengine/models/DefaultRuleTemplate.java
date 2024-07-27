package com.pw.rulesengine.models;

import com.pw.rulesengine.ruleengine.RuleTemplate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefaultRuleTemplate implements RuleTemplate {
    private String rulesetName;
    private String ruleId;
    private Integer priority;
    private String type;
    private String description;

    // MVEL Rules
    private DefaultExpression expression;

    // JAVA Rules
    private String className;

    // Spring Rules
    private DefaultSpringMethod springMethod;
}
