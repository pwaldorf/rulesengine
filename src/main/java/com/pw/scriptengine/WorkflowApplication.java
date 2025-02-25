package com.pw.scriptengine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

import com.pw.scriptengine.workflowloader.GroovyScript;
import com.pw.scriptengine.workflowloader.GwhScriptLoader;
import com.pw.scriptengine.workflowloader.GwhScriptTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WorkflowApplication {

	private static final String GROOVY_SCRIPT = """
        package com.pw.groovy.actions;

        import com.pw.workflowengine.workflowloader.GroovyScript
        import groovy.transform.CompileStatic

        @CompileStatic
        class GroovyScriptProcessor implements GroovyScript {
            boolean execute(Map map) {
                GroovyScriptProcessorA actiona = new GroovyScriptProcessorA()
                actiona.execute(map)

                return true
            }
        }
""";

	private static final String GROOVY_SCRIPTA = """
        package com.pw.groovy.actions;

        import com.pw.workflowengine.workflowloader.GroovyScript
        import groovy.transform.CompileStatic
        import com.pw.groovy.actionsb.GroovyScriptProcessorB

        @CompileStatic
        class GroovyScriptProcessorA implements GroovyScript {
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
                            GroovyScriptProcessorB actionb = new GroovyScriptProcessorB()
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

	private static final String GROOVY_SCRIPTB = """
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

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WorkflowApplication.class, args);

//		Map<String, Object> gmap = new LinkedHashMap<>();
//		gmap.put("SourceSystemName", "GTM");
//		gmap.put("StatusCode", "SOKS");
//		gmap.put("BuyCurrency", "CLS");
//		gmap.put("SenderBic", "CHASEUS33");
//		gmap.put("animal", "tiger");
//		MySampleGroovy mySampleGroovy = new MySampleGroovy();
//
//		measureExecutionTime(() -> {
//			for (int i = 0; i < 1000; i++) {
//				mySampleGroovy.execute(gmap);
//			}
//		}, "Groovy1");
//
//		measureExecutionTime(() -> {
//			for (int i = 0; i < 1000; i++) {
//				mySampleGroovy.execute(gmap);
//			}
//		}, "Groovy2");
//
//		measureExecutionTime(() -> {
//			for (int i = 0; i < 1000; i++) {
//				mySampleGroovy.execute(gmap);
//			}
//		}, "Groovy3");

		testGroovy();
	}


	//----------------------------------------------------------------
	public static void testGroovy() {
		// groovy execution

		Map<String, Object> mapg = new HashMap<>();
		mapg.put("SourceSystemName", "GTM");
		mapg.put("StatusCode1", "SOKS");
		mapg.put("BuyCurrency", "CLS");
        mapg.put("SellCurrency", "USD");
        mapg.put("StatusCode", "SOKS/");
        mapg.put("GroupKey", "GK123");
		mapg.put("SenderBic", "CHASEUS33");

//		SampleGroovy sampleGroovy = new SampleGroovy();
//		measureExecutionTime(() -> {
//			for (int i = 0; i < 1000; i++) {
//				sampleGroovy.groovyExecute(mapg);
//			}
//		}, "Workflow DurationG");
//
//		measureExecutionTime(() -> {
//			for (int i = 0; i < 1000; i++) {
//				sampleGroovy.groovyExecute(mapg);
//			}
//		}, "Workflow DurationG1");
//
//		measureExecutionTime(() -> {
//			for (int i = 0; i < 1000; i++) {
//				sampleGroovy.groovyExecute(mapg);
//			}
//		}, "Workflow DurationG2");
//
//		measureExecutionTime(() -> {
//			for (int i = 0; i < 1000; i++) {
//				sampleGroovy.groovyExecute(mapg);
//			}
//		}, "Workflow DurationG3");

		List<GwhScriptTemplate> gwhScriptTemplateList = new LinkedList<>();

		GwhScriptTemplate scriptTemplateB = new GwhScriptTemplate();
		scriptTemplateB.setScriptClassName("GroovyScriptProcessorB");
		scriptTemplateB.setScriptBody(GROOVY_SCRIPTB);
		gwhScriptTemplateList.add(scriptTemplateB);

		GwhScriptTemplate scriptTemplateA = new GwhScriptTemplate();
		scriptTemplateA.setScriptClassName("GroovyScriptProcessorA");
		scriptTemplateA.setScriptBody(GROOVY_SCRIPTA);
		gwhScriptTemplateList.add(scriptTemplateA);

		GwhScriptTemplate scriptTemplate = new GwhScriptTemplate();
		scriptTemplate.setScriptClassName("GroovyScriptProcessor");
		scriptTemplate.setScriptBody(GROOVY_SCRIPT);
		gwhScriptTemplateList.add(scriptTemplate);

		GwhScriptLoader scriptLoader = new GwhScriptLoader();
		GroovyScript groovyObject = scriptLoader.loadScript(gwhScriptTemplateList);

		measureExecutionTime(() -> {
			for (int i = 0; i < 1000; i++) {
				groovyObject.execute(mapg);
			}
		}, "Workflow DurationG4");

		measureExecutionTime(() -> {
			for (int i = 0; i < 1000; i++) {
				groovyObject.execute(mapg);
			}
		}, "Workflow DurationG5");

		// End Groovy
	}

	private static void measureExecutionTime(Runnable task, String taskName) {
		long startTime = System.nanoTime();
		task.run();
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(taskName + " Duration: " + duration);
	}
}