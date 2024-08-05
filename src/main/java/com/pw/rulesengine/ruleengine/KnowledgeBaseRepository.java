package com.pw.rulesengine.ruleengine;

import java.util.List;

import com.pw.rulesengine.rule.RuleTemplate;

public interface KnowledgeBaseRepository {

    public List<RuleTemplate> getAllRules();
    public List<RuleTemplate> getAllRuleByRuleSetName(String rulesetName);
}
