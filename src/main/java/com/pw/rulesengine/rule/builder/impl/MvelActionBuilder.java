package com.pw.rulesengine.rule.builder.impl;

import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.builder.ActionBuilder;
import com.pw.rulesengine.rule.impl.MvelAction;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@Service
public class MvelActionBuilder<T> implements ActionBuilder<T> {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Action<T> build(RuleTemplate ruleTemplate) {
        return new MvelAction(ruleTemplate.getExpression().getPassExpression(), ruleTemplate.getExpression().getContext());
    }

    @Override
    public String getType() {
        return "MVEL";
    }
}
