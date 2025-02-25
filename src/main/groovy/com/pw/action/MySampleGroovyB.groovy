package com.pw.action

import com.pw.scriptengine.workflowloader.GroovyScript
import groovy.transform.CompileStatic

@CompileStatic
class MySampleGroovyB implements GroovyScript {

    boolean execute(Map map) {
        def groupKey = map.get("GroupKey")
        if (groupKey != null) {
            map.put("SourceSystemId", groupKey)
        }

        String senderBic = (String) map.get("SenderBic")
        if (senderBic != null) {
            map.put("SwiftBic", senderBic)
            map.put("SsbOfficeCodeMap", senderBic)
        }
        return true;
    }
}