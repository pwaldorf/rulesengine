package com.pw.scriptengine;

import java.util.HashMap;
import java.util.Map;

import com.pw.scriptengine.scriptloader.GroovyScript;
import com.pw.scriptengine.scriptloader.GwhScriptLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WorkflowApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WorkflowApplication.class, args);

		testClassGroovy(context);
		testScriptGroovy(context);
	}

	public static void testClassGroovy(ConfigurableApplicationContext context) {

		Map<String, Object> mapg = new HashMap<>(Map.of(
				"SourceSystemName", "GTM",
				"StatusCode1", "SOKS",
				"BuyCurrency", "CLS",
				"SellCurrency", "USD",
				"StatusCode", "SOKS/",
				"GroupKey", "GK123",
				"SenderBic", "CHASEUS33"
		));

		GwhScriptLoader scriptLoader = context.getBean(GwhScriptLoader.class);
		GroovyScript groovyObject = scriptLoader.loadScript("SampleProcess");

		measureExecutionTime(() -> {
			for (int i = 0; i < 1000; i++) {
				groovyObject.execute(mapg);
			}
		}, "Workflow DurationG1");

		measureExecutionTime(() -> {
			for (int i = 0; i < 1000; i++) {
				groovyObject.execute(mapg);
			}
		}, "Workflow DurationG2");

	}

	public static void testScriptGroovy(ConfigurableApplicationContext context) {

		Map<String, Object> mapg = new HashMap<>(Map.of(
				"SourceSystemName", "GTM",
				"StatusCode1", "SOKS",
				"BuyCurrency", "CLS",
				"SellCurrency", "USD",
				"StatusCode", "SOKS/",
				"GroupKey", "GK123",
				"SenderBic", "CHASEUS33"
		));

		GwhScriptLoader scriptLoader = context.getBean(GwhScriptLoader.class);
		GroovyScript groovyObject = scriptLoader.loadScript("TestProcess");

		measureExecutionTime(() -> {
			for (int i = 0; i < 1000; i++) {
				groovyObject.execute(mapg);
			}
		}, "Workflow DurationG3");

		measureExecutionTime(() -> {
			for (int i = 0; i < 1000; i++) {
				groovyObject.execute(mapg);
			}
		}, "Workflow DurationG4");

	}

	private static void measureExecutionTime(Runnable task, String taskName) {
		long startTime = System.nanoTime();
		task.run();
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(taskName + " Duration: " + duration);
	}
}