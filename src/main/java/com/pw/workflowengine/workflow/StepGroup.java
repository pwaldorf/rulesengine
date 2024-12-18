package com.pw.workflowengine.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepGroup {

    @JsonProperty("stepGroupName")
    private String stepGroupName;

    @JsonProperty("stepTemplates")
    private List<StepTemplate> stepTemplates;

}
