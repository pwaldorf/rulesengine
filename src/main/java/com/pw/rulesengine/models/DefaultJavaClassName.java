package com.pw.rulesengine.models;

import com.pw.rulesengine.rule.JavaClassName;

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
