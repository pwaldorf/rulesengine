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

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "condition")
    private String condition;

    @Column(name = "pass")
    private String pass;

    @Column(name = "fail")
    private String fail;

    @Column(name = "always")
    private String always;

    @Column(name = "context")
    private String context;

    @Column(name = "condition_class_name")
    private String conditionClassName;

    @Column(name = "action_class_name")
    private String actionClassName;

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
