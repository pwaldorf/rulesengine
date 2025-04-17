package com.pw.action

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

@CompileStatic
@Component
class MySampleGroovyBImpl implements MySampleGroovyB {

    @Override
    void execute(Map<String, Object> context) {
        setSystemId(context)
        setSwiftBic(context)
    }

    void setSystemId(Map<String, Object> context) {
        String groupKey = (String) context.get("GroupKey")
        if (groupKey != null) {
            context.put("SourceSystemId", groupKey)
        }
    }

    void setSwiftBic(Map<String, Object> context) {

        String senderBic = (String) context.get("SenderBic")
        System.out.println("PJW IN Code")
        if (senderBic != null) {
            context.put("SwiftBic", senderBic)
            context.put("SsbOfficeCodeMap", senderBic)
        }
    }

}