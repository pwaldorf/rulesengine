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

    @Column(name = "type")
    private String type;

    @Column(name = "condition")
    private String condition;

    @Column(name = "action")
    private String action;

    @Column(name = "class_name")
    private String className;

    @Column(name = "condition_bean_name")
    private String conditionBeanName;

    @Column(name = "condition_bean_method")
    private String conditionBeanMethod;

    @Column(name = "action_bean_name")
    private String actionBeanName;

    @Column(name = "action_bean_method")
    private String actionBeanMethod;

    @Column(name = "description")
    private String description;

    @Data
    static class IdClass implements Serializable {
        private String rulesetName;
        private String ruleId;
    }
}
