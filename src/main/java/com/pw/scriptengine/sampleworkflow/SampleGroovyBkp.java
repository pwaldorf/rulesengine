package com.pw.scriptengine.sampleworkflow;

import com.pw.scriptengine.workflowloader.GroovyScript;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.util.Map;

public class SampleGroovyBkp {

    private static final Class<?> GROOVY_CLASS;
    private static final Class<?> GROOVY_CLASSA;

    private static final GroovyScript ACTION_INSTANCE;
    private static final GroovyScript ACTION_INSTANCEA;
//    private static final GroovyObject instance;

    //@groovy.transform.CompileStatic
    // Groovy script as a string
    private static final String GROOVY_SCRIPT = """
        package com.pw.groovy.actions;
        
        import com.pw.workflowengine.workflow.GroovyScript
        import groovy.transform.CompileStatic
    
        @CompileStatic
        class GroovyScriptProcessor implements GroovyScript {
        
            GroovyScriptProcessorA action;
            
            GroovyScriptProcessor(GroovyScriptProcessorA action) {
                this.action = action;
            }
    
            boolean execute(Map map) {
                String sourceSystemName = (String) map.get("SourceSystemName") // Most performant way in Groovy class
                if ("GTM".equalsIgnoreCase(sourceSystemName)) {
                    String buyCurrency = (String) map.get("BuyCurrency")
                    String sellCurrency = (String) map.get("SellCurrency")
    
                    if ("CLS".equalsIgnoreCase(buyCurrency) || "CLS".equalsIgnoreCase(sellCurrency)) {
                        // String statusCode = (String) map.get("StatusCode")
                        // if (statusCode != null && statusCode.endsWith("/")) { // Null check
                        //     statusCode = statusCode.substring(0, statusCode.length() - 1)
                        //     map.put("StatusCode", statusCode) // Update the map
                        // }
//                        GroovyScriptProcessorA action = new GroovyScriptProcessorA()
                        action.execute(map)

                        String statusCode = (String) map.get("StatusCode")
    
                        if ("SOKS".equalsIgnoreCase(statusCode)) {
                            def groupKey = map.get("GroupKey") // Avoid repeated map access
                            if (groupKey != null) { // Check for null before using the value
                                map.put("SourceSystemId", groupKey)
                            }
    
                            String senderBic = (String) map.get("SenderBic")
                            if (senderBic != null) { // Null check
                                map.put("SwiftBic", senderBic)
                                map.put("SsbOfficeCodeMap", senderBic)
                            }
                        }
                    } else {
                        map.put("Status", "IGNORE")
                        map.put("ReasonCode", "Status code " + map.get("StatusCode") + " can be ignored")
                    }
                }
                return true
            }
        }
    """;
    private static final String GROOVY_SCRIPTA = """
        package com.pw.groovy.actions;

        import com.pw.workflowengine.workflow.GroovyScript
        import groovy.transform.CompileStatic

        @CompileStatic
        class GroovyScriptProcessorA implements GroovyScript {

            boolean execute(Map map) {
                String statusCode = (String) map.get("StatusCode")
                if (statusCode != null && statusCode.endsWith("/")) { // Null check
                    statusCode = statusCode.substring(0, statusCode.length() - 1)
                    map.put("StatusCode", statusCode) // Update the map
                }
            }
        }
    """;

    static {
        // Use a static block to load and compile the Groovy class once
        CompilerConfiguration configuration = new CompilerConfiguration();
        configuration.setTargetBytecode("17");

        try (GroovyClassLoader gcl = new GroovyClassLoader(SampleGroovyBkp1.class.getClassLoader(), configuration);){
            GROOVY_CLASSA = gcl.parseClass(GROOVY_SCRIPTA);
            ACTION_INSTANCEA = (GroovyScript) GROOVY_CLASSA.getDeclaredConstructor().newInstance();
            GROOVY_CLASS = gcl.parseClass(GROOVY_SCRIPT);
            //ACTION_INSTANCE = (GroovyScript) GROOVY_CLASS.getDeclaredConstructor(GROOVY_CLASSA).newInstance(ACTION_INSTANCEA);
            ACTION_INSTANCE = (GroovyScript) GROOVY_CLASS.getDeclaredConstructor(GROOVY_CLASSA).newInstance(ACTION_INSTANCEA);
        } catch (Exception e) {
            throw new RuntimeException("Failed to compile and load Groovy script", e);
        }
    }

    public void groovyExecute(Map<String, Object> map) {
        ACTION_INSTANCE.execute(map);
    }
}