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

    // @Id
    // @Column(name = "step_group")
    // private String stepGroup;

    // @Column(name = "step_order")
    // private Integer stepOrder;

    @Column(name = "step_template")
    private String stepTemplate;

    @Column(name = "steps")
    private String steps;

    @Column(name = "description")
    private String description;

    @Data
    static class IdClass implements Serializable {
        private String processName;
        // private String stepGroup;
        // private Integer stepOrder;
    }
}
