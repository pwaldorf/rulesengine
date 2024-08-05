package com.pw.rulesengine.models;

import com.pw.rulesengine.rule.Expression;
import com.pw.rulesengine.rule.JavaClassName;
import com.pw.rulesengine.rule.RuleTemplate;
import com.pw.rulesengine.rule.SpringMethod;

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
    private String conditionType;
    private String passActionType;
    private String failActionType;
    private String alwaysActionType;
    private String statementType;
    private String description;

    // MVEL Rules
    private Expression expression;

    // JAVA Rules
    private JavaClassName javaClassName;

    // Spring Rules
    private SpringMethod springMethod;
}
