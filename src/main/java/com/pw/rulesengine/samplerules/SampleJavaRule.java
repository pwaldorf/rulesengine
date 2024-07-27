package com.pw.rulesengine.samplerules;

import java.util.Map;

import com.pw.rulesengine.rule.impl.JavaRule;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SampleJavaRule implements JavaRule<Map<String, Object>, Map<String, Object>> {

    @Override
    public boolean evaluate(Map<String, Object> context) {
        return (context.get("animal")).toString().equals("tiger");
    }

    @Override
    public void pass(Map<String, Object> context) {
        System.out.println("Java Rule Executed");
    }

}
