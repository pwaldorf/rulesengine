package com.pw.rulesengine;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.pw.rulesengine.controller.RulesController;
import com.pw.rulesengine.ruleengine.RuleEngine;

@SpringBootApplication
public class RulesengineApplication {

	public static void main(String[] args) {
		//SpringApplication.run(RulesengineApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(RulesengineApplication.class, args);


		RulesController<String, Map<String, Object>> rulesController
					= new RulesController<>((RuleEngine<String, Map<String, Object>>)context.getBean("simpleRuleEngine"));

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("debitAmount", Integer.valueOf(5));
		map.put("age", Integer.valueOf(20));
		map.put("name", "Bob");
		map.put("animal", "tiger");
		rulesController.runRules("NRT_RULESET_TEST1", map);
	}

}
