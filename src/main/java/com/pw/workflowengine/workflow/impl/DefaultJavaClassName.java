package com.pw.workflowengine.workflow.impl;

import com.pw.workflowengine.workflow.JavaClassName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DefaultJavaClassName implements JavaClassName{
    String conditionClassName;
    String passClassName;
    String failClassName;
    String alwaysClassName;
}
