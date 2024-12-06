package com.pw.workflowengine.workflow;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Step {

    @JsonProperty(value = "stepName", required = true)
    private String stepName;

    // JAVA, SPRING, MVEL, GROOVY, STEPGROUP
    @JsonProperty(value = "stepType", required = true)
    private String stepType;

    // Java - Full Class Name
    // Spring - Bean.Method
    // MVEL - MVEL Script
    // Groovy - Groovy Script
    @JsonProperty(value = "stepObject", required = true)
    private String stepObject;

    @JsonProperty("stepMethod")
    private String stepMethod;

    @JsonProperty("stepContext")
    private Map<String, String> stepContext;

    @JsonProperty("description")
    private String description;

}
