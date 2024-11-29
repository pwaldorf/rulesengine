package com.pw.workflowengine.sampleworkflow;

import java.util.Map;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class SampleGroovyScript {

    GroovyShell shell = null;
    Script script = null;

    // Groovy script as a string
    String groovyScript = """

        // Use a single access for frequently accessed values
        String sourceSystemName = map["SourceSystemName"] as String
        if ("GTM".equalsIgnoreCase(sourceSystemName)) {
            String buyCurrency = map["BuyCurrency"] as String
            String sellCurrency = map["SellCurrency"] as String

            // Combine checks for buyCurrency and sellCurrency
            if ("CLS".equalsIgnoreCase(buyCurrency) || "CLS".equalsIgnoreCase(sellCurrency)) {
                // Avoid reassigning variables; calculate and directly assign
                String statusCode = map["StatusCode"] as String
                if (statusCode.endsWith("/")) {
                    statusCode = statusCode.substring(0, statusCode.length() - 1) // Avoid range operation
                }

                if ("SOKS".equalsIgnoreCase(statusCode)) {
                    map["SourceSystemId"] = map["GroupKey"]

                    // SenderBic is reassigned without change; remove ternary redundancy
                    String senderBic = map["SenderBic"] as String
                    map["SwiftBic"] = senderBic

                    // Directly assign SwiftBic without intermediate variable
                    map["SsbOfficeCodeMap"] = senderBic
                }
            } else {
                // Use constants for repeated strings
                map["Status"] = "IGNORE"
                map["ReasonCode"] = "Status code ${map["StatusCode"]} can be ignored"
            }
        }
    """;

    public SampleGroovyScript() {

        shell = new GroovyShell();
        script = shell.parse(groovyScript);

    }

    public void groovyExecute(Map<String, Object> map) {

        // Binding binding = new Binding(map);
        // script.setBinding(binding);
        script.setProperty("map", map);
        script.run();
    }
}
