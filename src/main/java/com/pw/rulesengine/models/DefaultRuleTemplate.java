package com.pw.rulesengine.models;

import com.pw.rulesengine.rule.Expression;
import com.pw.rulesengine.rule.SpringMethod;
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
    private String conditionType;
    private String actionType;
    private String description;

    // MVEL Rules
    private Expression expression;

    // JAVA Rules
    private String conditionClassName;
    private String actionClassName;

    // Spring Rules
    private SpringMethod springMethod;
}
