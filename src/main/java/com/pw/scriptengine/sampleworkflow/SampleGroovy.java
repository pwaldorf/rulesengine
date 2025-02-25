package com.pw.scriptengine.sampleworkflow;

import com.pw.scriptengine.workflowloader.GroovyScript;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SampleGroovy {

    private final String GROOVY_SCRIPT= """
        package com.pw.groovy.actions;

        import com.pw.workflowengine.workflowloader.GroovyScript
        import groovy.transform.CompileStatic

        @CompileStatic
        class GroovyScriptProcessor implements GroovyScript {
            boolean execute(Map map) {
//                GroovyScript actionb = new GroovyScriptProcessorB()
//                GroovyScript actiona = new GroovyScriptProcessorA(actionb)
                GroovyScript actionb = new MySampleGroovyB()
                GroovyScript mySampleGroovy = new MySampleGroovy(actionb)
                actiona.execute(map)

                return true
            }
        }
""";

    private final String GROOVY_SCRIPTA = """
        package com.pw.groovy.actions;

        import com.pw.workflowengine.workflowloader.GroovyScript
        import groovy.transform.CompileStatic
        import com.pw.groovy.actionsb.GroovyScriptProcessorB

        @CompileStatic
        class GroovyScriptProcessorA implements GroovyScript {
            
             private final GroovyScript actionb;
             
             GroovyScriptProcessorA(GroovyScript actionb) {
                this.actionb = actionb;
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
//                            GroovyScript actionb = new GroovyScriptProcessorB()
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
    """;

    private final String GROOVY_SCRIPTB = """
        package com.pw.groovy.actionsb;

        import com.pw.workflowengine.workflowloader.GroovyScript
        import groovy.transform.CompileStatic

        @CompileStatic
        class GroovyScriptProcessorB implements GroovyScript {

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
    """;

    private GroovyObject groovyObject;
    private GroovyClassLoader groovyClassLoader;

    private GroovyScript ACTION_INSTANCEA;
    private GroovyScript ACTION_INSTANCEB;
    private ConcurrentHashMap<String, Class<?>> compiledClasses = new ConcurrentHashMap<>(); // Cache

    public SampleGroovy() {
        try {

//            List<>
            CompilerConfiguration config = new CompilerConfiguration();
            config.setScriptBaseClass(GroovyScript.class.getName());
            groovyClassLoader = new GroovyClassLoader(this.getClass().getClassLoader(), config);

            // Load and cache A, B, and the main script:
            Class<?> groovyClassB = loadAndCache(GROOVY_SCRIPTB, "GroovyScriptProcessorB");

            Class<?> groovyClassA = loadAndCache(GROOVY_SCRIPTA, "GroovyScriptProcessorA");

            Class<?> groovyClass = loadAndCache(GROOVY_SCRIPT, "GroovyScriptProcessor");
            groovyObject = (GroovyObject) groovyClass
                    .getDeclaredConstructor()
                    .newInstance();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error compiling or instantiating Groovy script", e);
        }
    }

    private Class<?> loadAndCache(String script, String className) {
        Class<?> cachedClass = compiledClasses.get(className);
        if (cachedClass != null) {
            return cachedClass; // Return from cache if available
        }

        try {
            Class<?> parsedClass = groovyClassLoader.parseClass(script);
            compiledClasses.put(className, parsedClass); // Store in the cache
            return parsedClass;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error compiling Groovy script: " + className, e);
        }
    }

    public void groovyExecute(Map<String, Object> map) {
        try {
            GroovyScript script = (GroovyScript) groovyObject;
            script.execute(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing Groovy script", e);
        }
    }

}