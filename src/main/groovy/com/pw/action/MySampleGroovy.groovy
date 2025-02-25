package com.pw.action;

import com.pw.scriptengine.workflowloader.GroovyScript
import groovy.transform.CompileStatic

@CompileStatic
class MySampleGroovy implements GroovyScript {

    private final GroovyScript actionb

    MySampleGroovy(GroovyScript actionb) {
        this.actionb = actionb
    }

    boolean execute(Map map) {
        String sourceSystemName = (String) map.get("SourceSystemName")
        if ("GTM".equalsIgnoreCase(sourceSystemName)) {
            String buyCurrency = (String) map.get("BuyCurrency")
            String sellCurrency = (String) map.get("SellCurrency")

            if ("CLS".equalsIgnoreCase(buyCurrency) || "CLS".equalsIgnoreCase(sellCurrency)) {
                String statusCode = (String) map.get("StatusCode")
                if (statusCode != null && statusCode.endsWith("/")) {
                    statusCode = statusCode.substring(0, statusCode.length() - 1)
                    map.put("StatusCode", statusCode)
                }

                if ("SOKS".equalsIgnoreCase(statusCode)) {
                    actionb.execute(map)
                }
            } else {
                map.put("Status", "IGNORE")
                map.put("ReasonCode", "Status code " + map.get("StatusCode") + " can be ignored")
            }
        }
        return true
    }
}