package com.pw.action;

import com.pw.scriptengine.scriptloader.GroovyScript
import groovy.transform.CompileStatic

@CompileStatic
class MySampleGroovy implements GroovyScript {

    private final GroovyScript actionb

    MySampleGroovy(GroovyScript actionb) {
        this.actionb = actionb
    }

    void execute(Map<String, Object> context) {
        String sourceSystemName = (String) context.get("SourceSystemName")
        if (!"GTM".equalsIgnoreCase(sourceSystemName)) {
            return
        }

        String buyCurrency = (String) context.get("BuyCurrency")
        String sellCurrency = (String) context.get("SellCurrency")
        String statusCode = (String) context.get("StatusCode")

        if (("CLS".equalsIgnoreCase(buyCurrency) || "CLS".equalsIgnoreCase(sellCurrency)) && statusCode != null) {
            if (statusCode.endsWith("/")) {
                statusCode = statusCode.substring(0, statusCode.length() - 1)
                context.put("StatusCode", statusCode)
            }

            if ("SOKS".equalsIgnoreCase(statusCode)) {
                actionb.execute(context)
            }
        } else {
            context.put("Status", "IGNORE")
            context.put("ReasonCode", "Status code " + context.get("StatusCode") + " can be ignored")
        }

    }
}