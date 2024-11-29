package com.pw.workflowengine.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pw.workflowengine.workflow.StepTemplate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Jacksonized
@SuppressWarnings("rawtypes")
public class StepTemplate {

    @JsonProperty(value = "stepGroupName", required = true)
    private String stepGroupName;

    @JsonProperty(value = "stepGroup", required = true)
    private String stepGroup;

    @JsonProperty(value = "stepOrder", required = true)
    private Integer stepOrder;

    @JsonProperty(value = "stepTemplateName", required = true)
    private String stepTemplateName;

    @JsonProperty(value = "stepRule", required = true)
    private String stepRuleName;

    private Action stepRuleAction;

    @JsonProperty("stepPass")
    private String stepPassName;

    private Action stepPassAction;

    @Builder.Default
    @JsonProperty("breakOnPass")
    private boolean breakOnPass = false;

    @JsonProperty("stepFail")
    private String stepFailName;

    private Action stepFailAction;

    @Builder.Default
    @JsonProperty("breakOnFail")
    private boolean breakOnFail = false;

    @JsonProperty("stepAlways")
    private String stepAlwaysName;

    private Action stepAlwaysAction;

    @JsonProperty("stepDescription")
    private String description;

}
