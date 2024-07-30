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
import com.pw.rulesengine.models.DefaultRuleTemplate;
import com.pw.rulesengine.models.DefaultSpringMethod;
import com.pw.rulesengine.rule.SpringMethod;
import com.pw.rulesengine.rule.Expression;
import com.pw.rulesengine.ruleengine.KnowledgeBaseRepository;
import com.pw.rulesengine.ruleengine.RuleTemplate;

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
                .context(StringUtils.isNotBlank(ruleDbModel.getContext()) ?
                                createMapFromDelimitedString(ruleDbModel.getContext()) : Collections.emptyMap())
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

        return DefaultRuleTemplate.builder()
                .rulesetName(ruleDbModel.getRulesetName().toUpperCase())
                .ruleId(ruleDbModel.getRuleId().toUpperCase())
                .priority(ruleDbModel.getPriority())
                .conditionType(ruleDbModel.getConditionType().toUpperCase())
                .actionType(ruleDbModel.getActionType().toUpperCase())
                .expression(expression)
                .conditionClassName(ruleDbModel.getConditionClassName())
                .actionClassName(ruleDbModel.getActionClassName())
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
