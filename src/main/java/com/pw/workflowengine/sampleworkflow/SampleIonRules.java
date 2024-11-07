package com.pw.workflowengine.sampleworkflow;

import java.util.Map;

public class SampleIonRules {

    public boolean isSourceSystemGTM(Map<String, Object> map) {
        return "GTM".equalsIgnoreCase(map.get("SourceSystemName").toString());
    }

    public boolean isCurrencyCLS(Map<String, Object> map) {
        return "CLS".equalsIgnoreCase(map.get("BuyCurrency").toString()) || "CLS".equalsIgnoreCase(map.get("SellCurrency").toString());
    }

    public boolean isSOKSStatusCode(Map<String, Object> map) {
        String statusCode = map.get("CLSStatusCode").toString();
        statusCode = statusCode.endsWith("/") ? statusCode.substring(0, statusCode.length() - 1) : statusCode.substring(1);
        return "SOKS".equalsIgnoreCase(statusCode);
    }

}
