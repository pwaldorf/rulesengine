package com.pw.workflowengine.sampleworkflow;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class TestPerfRules {

    public boolean isSourceSystemGTM(Map<String, Object> map) {
        return "GTM".equalsIgnoreCase((String)map.get("SourceSystemName"));
    }

    public boolean isCurrencyCLS(Map<String, Object> map) {
        return "CLS".equalsIgnoreCase((String)map.get("BuyCurrency")) || "CLS".equalsIgnoreCase((String)map.get("SellCurrency"));
    }

    public boolean isStatusCodeSOKS(Map<String, Object> map) {
        return "SOKS".equalsIgnoreCase((String)map.get("StatusCode"));
    }

    public boolean isFullSwiftBic(Map<String, Object> map) {
        return ((String)map.get("SenderBic")).length() == 12;
    }

    public void setStatusCode(Map<String, Object> map) {
        String statusCode = (String)map.get("StatusCode");
        statusCode = statusCode.endsWith("/") ? statusCode.substring(1, statusCode.length() - 1) : statusCode;
        map.put("StatusCode", statusCode);
    }

    public void setSourceSystemId(Map<String, Object> map) {
        map.put("SourceSystemId", map.get("GroupKey"));
    }

    public void setTwelveCharSwiftBic(Map<String, Object> map) {
        map.put("SwiftBic", (String) map.get("SenderBic"));
    }

    public void setSwiftBic(Map<String, Object> map) {
        map.put("SwiftBic", (String) map.get("SenderBic"));
    }

    public void setSsbOfficeCodeMap(Map<String, Object> map) {
        map.put("SsbOfficeCodeMap", (String) map.get("SwiftBic")); // Can change to lookup
    }

    public void processClsDtcc(Map<String, Object> map) {
        System.out.println("Message Processed");
    }

    public void setStatusAndReasonCode(Map<String, Object> map) {
        map.put("Status", "IGNORE");
        map.put("ReasonCode", "Status code " + map.get("StatusCode") + " can be ignored");
    }

}
