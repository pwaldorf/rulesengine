package com.pw.rulesengine.ruleengine;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.rulebuilder.RuleBuilder;
import com.pw.rulesengine.util.RuleCache;

@Component
public class RuleRepository {

    private final KnowledgeBaseRepository knowledgeBaseRepository;
    private final RuleBuilder ruleBuilder;
    private final RuleCache ruleCache;

    public RuleRepository(KnowledgeBaseRepository knowledgeBaseRepository, RuleBuilder ruleBuilder, RuleCache ruleCache) {
        this.knowledgeBaseRepository = knowledgeBaseRepository;
        this.ruleBuilder = ruleBuilder;
        this.ruleCache = ruleCache;
    }

    public Map<String, Rule> getByRuleset(String rulesetName) {

        if (MapUtils.isEmpty(ruleCache.getRuleset(rulesetName))) {
            knowledgeBaseRepository.getAllRuleByRuleSetName(rulesetName).forEach(
                ruleTemplate -> {
                         ruleCache.addRuleset(rulesetName, ruleTemplate.getRuleId(), (Rule)ruleBuilder.build(ruleTemplate));
            });
        }

        return ruleCache.getRuleset(rulesetName);
    }

}
