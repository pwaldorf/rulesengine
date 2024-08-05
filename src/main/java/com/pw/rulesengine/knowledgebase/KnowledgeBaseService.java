package com.pw.rulesengine.knowledgebase;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.pw.rulesengine.knowledgebase.db.RuleDbModel;
import com.pw.rulesengine.knowledgebase.db.RulesRepository;
import com.pw.rulesengine.models.DefaultExpression;
import com.pw.rulesengine.models.DefaultJavaClassName;
import com.pw.rulesengine.models.DefaultRuleTemplate;
import com.pw.rulesengine.models.DefaultSpringMethod;
import com.pw.rulesengine.rule.SpringMethod;
import com.pw.rulesengine.rule.Expression;
import com.pw.rulesengine.rule.JavaClassName;
import com.pw.rulesengine.rule.RuleTemplate;
import com.pw.rulesengine.ruleengine.KnowledgeBaseRepository;

@Service
public class KnowledgeBaseService implements KnowledgeBaseRepository{

    private final RulesRepository rulesRepository;

    public KnowledgeBaseService(RulesRepository rulesRepository) {
        this.rulesRepository = rulesRepository;
    }

    public List<RuleTemplate> getAllRules(){
        return rulesRepository.findAll().stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }

    public List<RuleTemplate> getAllRuleByRuleSetName(String rulesetName){
        return rulesRepository.findByRulesetName(rulesetName).stream()
                .map(
                        ruleDbModel -> mapFromDbModel(ruleDbModel)
                )
                .collect(Collectors.toList());
    }

    private RuleTemplate mapFromDbModel(RuleDbModel ruleDbModel){

        Expression expression = DefaultExpression.builder()
                .evaluateExpression(ruleDbModel.getCondition())
                .passExpression(ruleDbModel.getPass())
                .failExpression(ruleDbModel.getFail())
                .alwaysExpression(ruleDbModel.getAlways())
                .evaluateContext(StringUtils.isNotBlank(ruleDbModel.getConditionContext()) ?
                                createMapFromDelimitedString(ruleDbModel.getConditionContext()) : Collections.emptyMap())
                .passContext(StringUtils.isNotBlank(ruleDbModel.getPassContext()) ?
                                createMapFromDelimitedString(ruleDbModel.getPassContext()) : Collections.emptyMap())
                .failContext(StringUtils.isNotBlank(ruleDbModel.getFailContext()) ?
                                createMapFromDelimitedString(ruleDbModel.getFailContext()) : Collections.emptyMap())
                .alwaysContext(StringUtils.isNotBlank(ruleDbModel.getAlwaysContext()) ?
                                createMapFromDelimitedString(ruleDbModel.getAlwaysContext()) : Collections.emptyMap())
                .build();

        SpringMethod springMethod = DefaultSpringMethod.builder()
                .evaluateBeanName(ruleDbModel.getConditionBeanName())
                .evaluateMethodName(ruleDbModel.getConditionBeanMethod())
                .passBeanName(ruleDbModel.getPassBeanName())
                .passMethodName(ruleDbModel.getPassBeanMethod())
                .failBeanName(ruleDbModel.getFailBeanName())
                .failMethodName(ruleDbModel.getFailBeanMethod())
                .alwaysBeanName(ruleDbModel.getAlwaysBeanName())
                .alwaysMethodName(ruleDbModel.getAlwaysBeanMethod())
                .build();

        JavaClassName javaClassName = DefaultJavaClassName.builder()
            .conditionClassName(ruleDbModel.getConditionClassName())
            .passClassName(ruleDbModel.getPassClassName())
            .failClassName(ruleDbModel.getFailClassName())
            .alwaysClassName(ruleDbModel.getAlwaysClassName())
            .build();

        return DefaultRuleTemplate.builder()
                .rulesetName(ruleDbModel.getRulesetName().toUpperCase())
                .ruleId(ruleDbModel.getRuleId().toUpperCase())
                .priority(ruleDbModel.getPriority())
                .conditionType(ruleDbModel.getConditionType().toUpperCase())
                .passActionType(ruleDbModel.getPassActionType().toUpperCase())
                .failActionType(ruleDbModel.getFailActionType().toUpperCase())
                .alwaysActionType(ruleDbModel.getAlwaysActionType().toUpperCase())
                .statementType(getStatementType(ruleDbModel))
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

    private String getStatementType(RuleDbModel ruleDbModel) {

        if (StringUtils.isNotBlank(ruleDbModel.getAlwaysActionType())) {
            return "ALWAYS";
        }
        if (StringUtils.isNotBlank(ruleDbModel.getFailActionType())) {
            return "FAIL";
        }
        return "SIMPLE";
    }
}
