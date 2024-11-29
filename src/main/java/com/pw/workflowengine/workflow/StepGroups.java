package com.pw.workflowengine.workflow;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StepGroups {

    @JsonProperty("stepGroups")
    private List<StepGroup> stepGroups;

}
