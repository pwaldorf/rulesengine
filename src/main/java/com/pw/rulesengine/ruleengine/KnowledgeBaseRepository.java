package com.pw.rulesengine.ruleengine;

import java.util.List;

public interface KnowledgeBaseRepository {

    public List<RuleTemplate> getAllRules();
    public List<RuleTemplate> getAllRuleByRuleSetName(String rulesetName);
}
