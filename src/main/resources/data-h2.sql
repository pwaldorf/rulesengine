INSERT INTO script_template (id
                            , profile_name
                            , script_order
                            , script_name
                            , script_body
                            , description)
VALUES (
           1,
           'TestProcess',
           1,
           'GroovyScriptProcessorC',
           '
            package com.pw.groovy.actions

            import com.pw.scriptengine.scriptloader.GroovyScript
            import groovy.transform.CompileStatic
            import org.springframework.stereotype.Component
            import org.springframework.context.annotation.Primary

            @CompileStatic
            @Component
            @Primary
            class GroovyScriptProcessorC implements GroovyScript {

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
            }',
           'Test Script '
       );

INSERT INTO script_template (id
                            , profile_name
                            , script_order
                            , script_name
                            , script_body
                            , description)
VALUES (
           2,
           'TestProcess',
           2,
           'groovyScriptProcessorA',
           '
        package com.pw.groovy.actions

        import com.pw.scriptengine.scriptloader.GroovyScript
        import groovy.transform.CompileStatic
        import org.springframework.stereotype.Component
        import org.springframework.context.annotation.Primary

        @CompileStatic
        @Primary
        @Component
        class GroovyScriptProcessorA implements GroovyScript {
             private final GroovyScriptProcessorC actionb

             GroovyScriptProcessorA(GroovyScriptProcessorC actionb) {
                 this.actionb = actionb
             }

             GroovyScriptProcessorA() {

              }

             void execute(Map<String, Object> context) {
                String sourceSystemName = (String) context.get("SourceSystemName")
                if ("GTM".equalsIgnoreCase(sourceSystemName)) {
                    String buyCurrency = (String) context.get("BuyCurrency")
                    String sellCurrency = (String) context.get("SellCurrency")

                    if ("CLS".equalsIgnoreCase(buyCurrency) || "CLS".equalsIgnoreCase(sellCurrency)) {
                        setStatusCode(context)

                        if ("SOKS".equalsIgnoreCase((String) context.get("StatusCode"))) {
                            actionb.execute(context)
                        }
                    } else {
                        context.put("Status", "IGNORE")
                        context.put("ReasonCode", "Status code " + context.get("StatusCode") + " can be ignored")
                    }
                }
            }

            void setStatusCode(Map<String, Object> context) {
                String statusCode = (String) context.get("StatusCode")
                if (statusCode != null && statusCode.endsWith("/")) {
                    statusCode = statusCode.substring(0, statusCode.length() - 1)
                    context.put("StatusCode", statusCode)
                }
            }
        }',
           'Test Script'
       );