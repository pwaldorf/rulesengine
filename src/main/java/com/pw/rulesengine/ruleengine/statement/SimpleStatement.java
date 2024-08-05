package com.pw.rulesengine.ruleengine.statement;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.ruleengine.Statement;

@Component
public class SimpleStatement  implements Statement<Rule, Map<String, Object>> {

    public void execute(Rule rule, Map<String, Object> context) {
        if (rule.getCondition().evaluate(context)) {
            rule.getPassAction().execute(context);
        }
    }

    public String getType() {
        return "SIMPLE";
    }

}
