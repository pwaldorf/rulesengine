package com.pw.scriptengine.workflowloader.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pw.scriptengine.workflowloader.db.WorkflowDbModel.IdClass;

// import jakarta.persistence.OrderBy;

@Repository
public interface WorkflowRepository extends JpaRepository<WorkflowDbModel, IdClass> {

    // @OrderBy("StepGroup,StepOrder")
    // @OrderBy("stepOrder")
    WorkflowDbModel findByProcessName(String processName);
    @SuppressWarnings("null")
    List<WorkflowDbModel> findAll();
}