package com.pw.workflowengine.sampleworkflow;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TestPerfSingleClass {
    public void clsWorkFlowExample(Map<String, Object> map) {


        if ("GTM".equalsIgnoreCase((String)map.get("SourceSystemName"))) {
            if ("CLS".equalsIgnoreCase((String)map.get("BuyCurrency")) || "CLS".equalsIgnoreCase((String)map.get("SellCurrency"))) {
                String statusCode = (String)map.get("StatusCode");
                statusCode = statusCode.endsWith("/") ? statusCode.substring(1, statusCode.length() - 1) : statusCode;

                if ("SOKS".equalsIgnoreCase(statusCode)) {
                    map.put("SourceSystemId", map.get("GroupKey"));

                    if (((String)map.get("SenderBic")).length() == 12) {
                        map.put("SwiftBic", (String) map.get("SenderBic"));
                    } else {
                        map.put("SwiftBic", (String) map.get("SenderBic"));
                    }

                    map.put("SsbOfficeCodeMap", (String) map.get("SwiftBic"));
                }
            } else {
                map.put("Status", "IGNORE");
                map.put("ReasonCode", "Status code " + map.get("StatusCode") + " can be ignored");
            }
        }
    }
}
