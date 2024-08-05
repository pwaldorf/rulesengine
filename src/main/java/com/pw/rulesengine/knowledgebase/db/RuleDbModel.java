package com.pw.rulesengine.knowledgebase.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.IdClass;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rules")
@IdClass(RuleDbModel.IdClass.class)
public class RuleDbModel {
    @Id
    @Column(name = "ruleset_name")
    private String rulesetName;

    @Id
    @Column(name = "rule_id")
    private String ruleId;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "condition_type")
    private String conditionType;

    @Column(name = "pass_action_type")
    private String passActionType;

    @Column(name = "fail_action_type")
    private String failActionType;

    @Column(name = "always_action_type")
    private String alwaysActionType;

    // MVEL Fields
    @Column(name = "condition_context")
    private String conditionContext;

    @Column(name = "condition")
    private String condition;

    @Column(name = "pass_context")
    private String passContext;

    @Column(name = "pass")
    private String pass;

    @Column(name = "fail_context")
    private String failContext;

    @Column(name = "fail")
    private String fail;

    @Column(name = "always_context")
    private String alwaysContext;

    @Column(name = "always")
    private String always;

    // JAVA Fields
    @Column(name = "condition_class_name")
    private String conditionClassName;

    @Column(name = "pass_class_name")
    private String passClassName;

    @Column(name = "fail_class_name")
    private String failClassName;

    @Column(name = "always_class_name")
    private String alwaysClassName;

    // Spring Fields
    @Column(name = "condition_bean_name")
    private String conditionBeanName;

    @Column(name = "condition_bean_method")
    private String conditionBeanMethod;

    @Column(name = "pass_bean_name")
    private String passBeanName;

    @Column(name = "pass_bean_method")
    private String passBeanMethod;

    @Column(name = "fail_bean_name")
    private String failBeanName;

    @Column(name = "fail_bean_method")
    private String failBeanMethod;

    @Column(name = "always_bean_name")
    private String alwaysBeanName;

    @Column(name = "always_bean_method")
    private String alwaysBeanMethod;

    @Column(name = "description")
    private String description;

    @Data
    static class IdClass implements Serializable {
        private String rulesetName;
        private String ruleId;
    }
}
