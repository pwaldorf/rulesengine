package com.pw.rulesengine.rulebuilder.impl;

import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.RuleTemplate;
import com.pw.rulesengine.rule.impl.MvelAction;
import com.pw.rulesengine.rulebuilder.ActionBuilder;
import com.pw.rulesengine.rulebuilder.model.MvelExpression;

@Service
public class MvelActionBuilder<U> implements ActionBuilder<U> {

    @Override
    public String getType() {
        return "MVEL";
    }

    @Override
    public Action<U> build(String type, RuleTemplate ruleTemplate) {
        MvelExpression mvelExpression = new MvelExpression();
        if (type.toUpperCase().equals("PASS")) {
            mvelExpression.setExpression(ruleTemplate.getExpression().getPassExpression());
            mvelExpression.setContext(ruleTemplate.getExpression().getPassContext());
            return new MvelAction<U>(mvelExpression);
        }

        if (type.toUpperCase().equals("FAIL")) {
            mvelExpression.setExpression(ruleTemplate.getExpression().getFailExpression());
            mvelExpression.setContext(ruleTemplate.getExpression().getFailContext());
            return new MvelAction<U>(mvelExpression);
        }

        mvelExpression.setExpression(ruleTemplate.getExpression().getAlwaysExpression());
        mvelExpression.setContext(ruleTemplate.getExpression().getAlwaysContext());
        return new MvelAction<U>(mvelExpression);
    }
}
