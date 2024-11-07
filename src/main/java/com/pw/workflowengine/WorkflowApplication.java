package com.pw.workflowengine;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.pw.workflowengine.controller.WorkflowController;
import com.pw.workflowengine.sampleworkflow.TestPerfMvel;
import com.pw.workflowengine.sampleworkflow.TestPerfSingleClass;
import com.pw.workflowengine.stepengine.StepEngine;

@SpringBootApplication
public class WorkflowApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WorkflowApplication.class, args);


		@SuppressWarnings("unchecked")
		WorkflowController rulesController
					= new WorkflowController((StepEngine<String, Object>)context.getBean("simpleWorkflowEngine"));


		//-------------------------------------------------

		Map<String, Object> map1 = new HashMap<>();
			Map<String, Object> map2 = new HashMap<>();
			map1.put("SourceSystemName", "GTM");
			map1.put("StatusCode1", "SOKS");
			map1.put("BuyCurrency", "CLS");
			map1.put("SenderBic", "CHASEUS33");
			map2.put("statusCode", " ");
			map2.put("swiftBic", "12");
			map2.put("SsbOfficeCodeMap", " ");
			map2.put("Status", " ");
			map2.put("ReasonCode", " ");

		TestPerfMvel testPerfMvel = new TestPerfMvel();
		testPerfMvel.execute(map1, map2);

		long startTimeA = System.nanoTime();
		for (int i=0; i <1000; i++) {

			testPerfMvel.execute(map1, map2);
		}

		long endTimeA = System.nanoTime();
		long durationA = (endTimeA - startTimeA);
		System.out.println("Workflow DurationA: " + durationA);

		long startTimeB = System.nanoTime();
		for (int i=0; i <1000; i++) {

			testPerfMvel.execute(map1, map2);
		}

		long endTimeB = System.nanoTime();
		long durationB = (endTimeB - startTimeB);
		System.out.println("Workflow DurationB: " + durationB);

		//-------------------------------------------------
		Map<String, Object> map = new LinkedHashMap<>();
			map.put("SourceSystemName", "GTM");
			map.put("StatusCode", "SOKS");
			map.put("BuyCurrency", "CLS");
			map.put("SenderBic", "CHASEUS33");
		rulesController.runRules("NRT_RULESET_TEST1", map);

		long startTime = System.nanoTime();

		for (int i=0; i <1000; i++) {

			// map.put("age", Integer.valueOf(20));
			// map.put("name", "Bob");
			// map.put("animal", "tiger");
			rulesController.runRules("NRT_RULESET_TEST1", map);
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Workflow Duration: " + duration);

		//-------------------------------------------------
		Map<String, Object> map3 = new LinkedHashMap<>();
			map3.put("SourceSystemName", "GTM");
			map3.put("StatusCode", "SOKS");
			map3.put("BuyCurrency", "CLS");
			map3.put("SenderBic", "CHASEUS33");

		long startTime2 = System.nanoTime();


		for (int i=0; i <100; i++) {
			// map.put("age", Integer.valueOf(20));
			// map.put("name", "Bob");
			// map.put("animal", "tiger");
			rulesController.runRules("NRT_RULESET_TEST1", map3);
		}

		long endTime2 = System.nanoTime();
		long duration2 = (endTime2 - startTime2);
		System.out.println("Workflow2 Duration: " + duration2);

		// long startTime3 = System.nanoTime();

		// for (int i=0; i <100; i++) {
		// 	Map<String, Object> map = new LinkedHashMap<>();
		// 	map.put("SourceSystemName", "GTM");
		// 	map.put("StatusCode", "SOKS");
		// 	map.put("BuyCurrency", "CLS");
		// 	map.put("SenderBic", "CHASEUS33");
		// 	// map.put("age", Integer.valueOf(20));
		// 	// map.put("name", "Bob");
		// 	// map.put("animal", "tiger");
		// 	rulesController.runRules("NRT_RULESET_TEST1", map);
		// }

		// long endTime3 = System.nanoTime();
		// long duration3 = (endTime3 - startTime3);
		// System.out.println("Workflow3 Duration: " + duration3);

		//-------------------------------------------------
		Map<String, Object> map5 = new LinkedHashMap<>();
			map5.put("SourceSystemName", "GTM");
			map5.put("StatusCode", "SOKS");
			map5.put("BuyCurrency", "CLS");
			map5.put("SenderBic", "CHASEUS33");
		long startTime1 = System.nanoTime();
		TestPerfSingleClass testPerfSingleClass = context.getBean(TestPerfSingleClass.class);
		for (int j=0; j <100; j++) {
			testPerfSingleClass.clsWorkFlowExample(map5);
		}
		long endTime1 = System.nanoTime();
		long duration1 = (endTime1 - startTime1);
		System.out.println("Spring Duration: " + duration1);
	}
}
