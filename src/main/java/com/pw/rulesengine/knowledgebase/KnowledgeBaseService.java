package com.pw.rulesengine.knowledgebase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pw.rulesengine.knowledgebase.db.RuleDbModel;
import com.pw.rulesengine.knowledgebase.db.RulesRepository;
import com.pw.rulesengine.models.DefaultRuleTemplate;
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

        return DefaultRuleTemplate.builder()
                .rulesetName(ruleDbModel.getRulesetName().toUpperCase())
                .ruleId(ruleDbModel.getRuleId().toUpperCase())
                .type(ruleDbModel.getType().toUpperCase())
                .condition(ruleDbModel.getCondition())
                .action(ruleDbModel.getAction())
                .className(ruleDbModel.getClassName())
                .evaluateBeanName(ruleDbModel.getConditionBeanName())
                .evaluateMethodName(ruleDbModel.getConditionBeanMethod())
                .executeBeanName(ruleDbModel.getActionBeanName())
                .executeMethodName(ruleDbModel.getActionBeanMethod())
                .description(ruleDbModel.getDescription())
                .priority(ruleDbModel.getPriority())
                .build();
    }
}
