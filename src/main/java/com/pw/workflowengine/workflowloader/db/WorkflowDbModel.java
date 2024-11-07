package com.pw.workflowengine.workflowloader.db;

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
@Table(name = "workflow")
@IdClass(WorkflowDbModel.IdClass.class)
public class WorkflowDbModel {
    @Id
    @Column(name = "process_name")
    private String processName;

    @Id
    @Column(name = "step_group")
    private String stepGroup;

    @Column(name = "step_order")
    private Integer stepOrder;

    @Column(name = "step_rule_type")
    private String stepRuleType;

    @Column(name = "step_pass_type")
    private String stepPassType;

    @Column(name = "step_fail_type")
    private String stepFailType;

    @Column(name = "step_always_type")
    private String stepAlwaysType;

    @Column(name = "break_on_pass")
    private Boolean breakOnPass;

    @Column(name = "break_on_fail")
    private Boolean breakOnFail;

    // MVEL Fields
    @Column(name = "step_rule_context")
    private String stepRuleContext;

    @Column(name = "step_rule")
    private String stepRule;

    @Column(name = "step_pass_context")
    private String stepPassContext;

    @Column(name = "step_pass")
    private String stepPass;

    @Column(name = "step_fail_context")
    private String stepFailContext;

    @Column(name = "step_fail")
    private String stepFail;

    @Column(name = "step_always_context")
    private String stepAlwaysContext;

    @Column(name = "step_always")
    private String stepAlways;

    // JAVA Fields
    @Column(name = "step_rule_class_name")
    private String stepRuleClassName;

    @Column(name = "step_pass_class_name")
    private String stepPassClassName;

    @Column(name = "step_fail_class_name")
    private String stepFailClassName;

    @Column(name = "step_always_class_name")
    private String stepAlwaysClassName;

    // Spring Fields
    @Column(name = "step_rule_bean_name")
    private String stepRuleBeanName;

    @Column(name = "step_rule_bean_method")
    private String stepRuleBeanMethod;

    @Column(name = "step_pass_bean_name")
    private String stepPassBeanName;

    @Column(name = "step_pass_bean_method")
    private String stepPassBeanMethod;

    @Column(name = "step_fail_bean_name")
    private String stepFailBeanName;

    @Column(name = "step_fail_bean_method")
    private String stepFailBeanMethod;

    @Column(name = "step_always_bean_name")
    private String stepAlwaysBeanName;

    @Column(name = "step_always_bean_method")
    private String stepAlwaysBeanMethod;

    @Column(name = "description")
    private String description;

    @Data
    static class IdClass implements Serializable {
        private String processName;
        private String stepGroup;
        private Integer stepOrder;
    }
}
