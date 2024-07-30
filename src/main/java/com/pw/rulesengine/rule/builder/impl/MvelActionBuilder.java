package com.pw.rulesengine.rule.builder.impl;

import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.builder.ActionBuilder;
import com.pw.rulesengine.rule.impl.MvelAction;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@Service
public class MvelActionBuilder<U> implements ActionBuilder<U> {

    public Action<U> build(RuleTemplate ruleTemplate) {
        return new MvelAction<U>(ruleTemplate.getExpression().getPassExpression(), ruleTemplate.getExpression().getContext());
    }

    @Override
    public String getType() {
        return "MVEL";
    }
}
