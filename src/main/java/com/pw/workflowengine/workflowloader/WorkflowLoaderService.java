package com.pw.workflowengine.workflowloader;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.pw.workflowengine.stepengine.WorkflowResource;
import com.pw.workflowengine.workflow.Expression;
import com.pw.workflowengine.workflow.JavaClassName;
import com.pw.workflowengine.workflow.StepTemplate;
import com.pw.workflowengine.workflow.impl.DefaultExpression;
import com.pw.workflowengine.workflow.impl.DefaultJavaClassName;
import com.pw.workflowengine.workflow.impl.DefaultSpringMethod;
import com.pw.workflowengine.workflow.impl.DefaultStepTemplate;
import com.pw.workflowengine.workflow.SpringMethod;
import com.pw.workflowengine.workflowloader.db.WorkflowDbModel;
import com.pw.workflowengine.workflowloader.db.WorkflowRepository;

@Service
public class WorkflowLoaderService implements WorkflowResource<String, Object>{

    private final WorkflowRepository rulesRepository;

    public WorkflowLoaderService(WorkflowRepository rulesRepository) {
        this.rulesRepository = rulesRepository;
    }

    public List<StepTemplate<String, Object>> getAllSteps(){
        return rulesRepository.findAll().stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }

    public List<StepTemplate<String, Object>> getAllStepsByProcessName(String rulesetName){
        return rulesRepository.findByProcessNameOrderByStepOrderAsc(rulesetName).stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }

    private StepTemplate<String, Object> mapFromDbModel(WorkflowDbModel ruleDbModel){

        Expression<String, Object> expression = DefaultExpression.builder()
                .evaluateExpression(ruleDbModel.getStepRule())
                .passExpression(ruleDbModel.getStepPass())
                .failExpression(ruleDbModel.getStepFail())
                .alwaysExpression(ruleDbModel.getStepAlways())
                .evaluateContext(StringUtils.isNotBlank(ruleDbModel.getStepRuleContext()) ?
                                createMapFromDelimitedString(ruleDbModel.getStepRuleContext()) : Collections.emptyMap())
                .passContext(StringUtils.isNotBlank(ruleDbModel.getStepPassContext()) ?
                                createMapFromDelimitedString(ruleDbModel.getStepPassContext()) : Collections.emptyMap())
                .failContext(StringUtils.isNotBlank(ruleDbModel.getStepFailContext()) ?
                                createMapFromDelimitedString(ruleDbModel.getStepFailContext()) : Collections.emptyMap())
                .alwaysContext(StringUtils.isNotBlank(ruleDbModel.getStepAlwaysContext()) ?
                                createMapFromDelimitedString(ruleDbModel.getStepAlwaysContext()) : Collections.emptyMap())
                .build();

        SpringMethod springMethod = DefaultSpringMethod.builder()
                .evaluateBeanName(ruleDbModel.getStepRuleBeanName())
                .evaluateMethodName(ruleDbModel.getStepRuleBeanMethod())
                .passBeanName(ruleDbModel.getStepPassBeanName())
                .passMethodName(ruleDbModel.getStepPassBeanMethod())
                .failBeanName(ruleDbModel.getStepFailBeanName())
                .failMethodName(ruleDbModel.getStepFailBeanMethod())
                .alwaysBeanName(ruleDbModel.getStepAlwaysBeanName())
                .alwaysMethodName(ruleDbModel.getStepAlwaysBeanMethod())
                .build();

        JavaClassName javaClassName = DefaultJavaClassName.builder()
            .conditionClassName(ruleDbModel.getStepRuleClassName())
            .passClassName(ruleDbModel.getStepPassClassName())
            .failClassName(ruleDbModel.getStepFailClassName())
            .alwaysClassName(ruleDbModel.getStepAlwaysClassName())
            .build();

        return DefaultStepTemplate.builder()
            .processName(ruleDbModel.getProcessName().toUpperCase())
            .stepGroup(ruleDbModel.getStepGroup().toUpperCase())
            .order(ruleDbModel.getStepOrder())
            .stepRuleType(ruleDbModel.getStepRuleType().toUpperCase())
            .stepPassType(ruleDbModel.getStepPassType().toUpperCase())
            .stepFailType(ruleDbModel.getStepFailType().toUpperCase())
            .stepAlwaysType(ruleDbModel.getStepAlwaysType().toUpperCase())
            .breakOnPass(ruleDbModel.getBreakOnPass())
            .breakOnFail(ruleDbModel.getBreakOnFail())
            .expression(expression)
            .javaClassName(javaClassName)
            .springMethod(springMethod)
            .description(ruleDbModel.getDescription())
            .build();
    }

    private Map<String, Object> createMapFromDelimitedString(String input) {
        Map<String, Object> map = new HashMap<>();

        // Split the input string by commas to get key-value pairs
        String[] pairs = input.split(",");

        for (String pair : pairs) {
            // Split each pair by the colon to get the key and value
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                map.put(key, value);
            }
        }
        return map;
    }

}
