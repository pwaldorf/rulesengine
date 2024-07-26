package com.pw.rulesengine.rule.builder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.rule.builder.RuleBuilder;
import com.pw.rulesengine.rule.impl.JavaRule;
import com.pw.rulesengine.ruleengine.RuleTemplate;

@Service
public class JavaRuleBuilder implements RuleBuilder {

    private final ApplicationContext applicationContext;

    public JavaRuleBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Rule build(RuleTemplate ruleTemplate) {
        try {
            ClassLoader classLoader = applicationContext.getClassLoader();
            Class<? extends JavaRule> ruleClass = (Class<? extends JavaRule>)classLoader.loadClass(ruleTemplate.getClassName());
            // Class<? extends JavaRule> ruleClass = (Class<? extends JavaRule>) Class.forName(ruleTemplate.getClassName());
            return ruleClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create rule instance", e);
        }
    }

    @Override
    public String getType() {
        return "JAVA";
    }

}
