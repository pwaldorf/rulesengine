package com.pw.workflowengine.workflow;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Steps {

    @JsonProperty("steps")
    List<Step> steps;
}
