package com.pw.rulesengine;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RulesengineApplication {

	public static void main(String[] args) {
		//SpringApplication.run(RulesengineApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(RulesengineApplication.class, args);

		RulesController rulesController = context.getBean(RulesController.class);

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("debitAmount", Integer.valueOf(5));
		map.put("age", Integer.valueOf(20));
		map.put("name", "Bob");
		map.put("animal", "tiger");
		rulesController.runRules("NRT_RULESET_TEST1", map);
	}

}