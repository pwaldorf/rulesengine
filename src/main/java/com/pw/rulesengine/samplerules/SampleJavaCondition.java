package com.pw.rulesengine.samplerules;

import java.util.Map;

import com.pw.rulesengine.rule.impl.JavaCondition;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SampleJavaCondition implements JavaCondition<Map<String, Object>> {

    @Override
    public boolean evaluate(Map<String, Object> context) {
        return (context.get("animal")).toString().equals("tiger");
    }
}
