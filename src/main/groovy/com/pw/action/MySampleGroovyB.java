package com.pw.action

import com.pw.scriptengine.scriptloader.GroovyScript
import groovy.transform.CompileStatic

@CompileStatic
class MySampleGroovyB implements GroovyScript {

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
        if (senderBic != null) {
            context.put("SwiftBic", senderBic)
            context.put("SsbOfficeCodeMap", senderBic)
        }
    }

}