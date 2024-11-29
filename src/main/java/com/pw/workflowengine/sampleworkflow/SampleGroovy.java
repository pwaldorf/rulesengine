package com.pw.workflowengine.sampleworkflow;

import java.util.Map;

import com.pw.workflowengine.workflow.GroovyScript;

import groovy.lang.GroovyClassLoader;

public class SampleGroovy {

    Class<?> groovyClass = null;
    GroovyScript action = null;

    // Groovy script as a string
    String groovyScript = """

    import com.pw.workflowengine.workflow.GroovyScript

    @groovy.transform.CompileStatic
    class GroovyScriptProcessor implements GroovyScript {

        boolean execute(Map map) {
            String sourceSystemName = map["SourceSystemName"] as String
            if ("GTM".equalsIgnoreCase(sourceSystemName)) {
                String buyCurrency = map["BuyCurrency"] as String
                String sellCurrency = map["SellCurrency"] as String

                if ("CLS".equalsIgnoreCase(buyCurrency) || "CLS".equalsIgnoreCase(sellCurrency)) {
                    String statusCode = map["StatusCode"] as String
                    if (statusCode.endsWith("/")) {
                        statusCode = statusCode.substring(0, statusCode.length() - 1) // Avoid range operation
                    }

                    if ("SOKS".equalsIgnoreCase(statusCode)) {
                        map["SourceSystemId"] = map["GroupKey"]

                        String senderBic = map["SenderBic"] as String
                        map["SwiftBic"] = senderBic

                        map["SsbOfficeCodeMap"] = senderBic
                    }
                } else {
                    map["Status"] = "IGNORE"
                    map["ReasonCode"] = "Status code ${map["StatusCode"]} can be ignored"
                }
            }
            return true
        }
    }
    """;

    @SuppressWarnings({ "unchecked"})
    public SampleGroovy() {

        try (var gcl = new GroovyClassLoader();) {
            action = (GroovyScript) gcl.parseClass(groovyScript).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void groovyExecute(Map<String, Object> map) {
            action.execute(map);
    }

}
