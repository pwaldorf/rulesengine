package com.pw.rulesengine.ruleengine.statement;

import java.util.Map;
import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.ruleengine.Statement;

public class IfElseStatement implements Statement<Rule, Map<String, Object>> {

    public void execute(Rule rule, Map<String, Object> context) {
        if (rule.getCondition().evaluate(context)) {
            rule.getPassAction().execute(context);
        } else {
            rule.getFailAction().execute(context);
        }
    }

    public String getType() {
        return "FAIL";
    }
}
