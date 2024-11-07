package com.pw.workflowengine.workflowloader.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pw.workflowengine.workflowloader.db.WorkflowDbModel.IdClass;

import jakarta.persistence.OrderBy;

@Repository
public interface WorkflowRepository extends JpaRepository<WorkflowDbModel, IdClass> {

    // @OrderBy("StepGroup,StepOrder")
    // @OrderBy("stepOrder")
    List<WorkflowDbModel> findByProcessNameOrderByStepOrderAsc(String processName);
    @SuppressWarnings("null")
    List<WorkflowDbModel> findAll();
}
