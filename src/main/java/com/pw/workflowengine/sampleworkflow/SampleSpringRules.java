package com.pw.workflowengine.sampleworkflow;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SampleSpringRules {

    public boolean isAdult(Map<String, Object> map) {

        return (Integer) (map.get("age")) >= 18;
    }

    // public boolean isChild(int age) {
    //     return age < 18;
    // }

    public void sayHello(Map<String, Object> map) {
        System.out.println("Hello " + map.get("name"));
    }

}
