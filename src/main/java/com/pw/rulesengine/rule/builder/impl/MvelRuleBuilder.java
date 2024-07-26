package com.pw.rulesengine.rule.builder.impl;

import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.rule.builder.RuleBuilder;
import com.pw.rulesengine.rule.impl.MvelRule;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@Service
public class MvelRuleBuilder implements RuleBuilder {

    @Override
    public Rule build(RuleTemplate ruleTemplate) {
        return new MvelRule(ruleTemplate.getCondition(), ruleTemplate.getAction());
    }

    @Override
    public String getType() {
        return "MVEL";
    }

}
