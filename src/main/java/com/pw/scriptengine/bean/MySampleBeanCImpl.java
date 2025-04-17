package com.pw.scriptengine.bean;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MySampleBeanCImpl implements MySampleBeanC{

    @Override
    public void execute(Map<String, Object> context) {
        setSystemId(context);
    }

    private void setSystemId(Map<String, Object> context) {
        System.out.println("pjw in java class");
        String groupKey = (String) context.get("GroupKey");
        if (groupKey != null) {
            context.put("SourceSystemId", groupKey);
        }
    }
}