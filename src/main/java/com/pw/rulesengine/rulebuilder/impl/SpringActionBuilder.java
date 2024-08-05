package com.pw.rulesengine.rulebuilder.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.RuleTemplate;
import com.pw.rulesengine.rule.impl.SpringAction;
import com.pw.rulesengine.rulebuilder.ActionBuilder;
import com.pw.rulesengine.rulebuilder.model.SpringBean;

@Service
public class SpringActionBuilder<U> implements ActionBuilder<U> {
    private final ApplicationContext applicationContext;

    public SpringActionBuilder(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Action<U> build(String type, RuleTemplate ruleTemplate) {

        if (type.toUpperCase().equals("PASS")) {
            SpringBean springBean = new SpringBean();
            springBean.setBeanName(ruleTemplate.getSpringMethod().getPassBeanName());
            springBean.setMethodName(ruleTemplate.getSpringMethod().getPassMethodName());
            return new SpringAction<U>(applicationContext, springBean);
        }

        if (type.toUpperCase().equals("FAIL")) {
            SpringBean springBean = new SpringBean();
            springBean.setBeanName(ruleTemplate.getSpringMethod().getFailBeanName());
            springBean.setMethodName(ruleTemplate.getSpringMethod().getFailMethodName());
            return new SpringAction<U>(applicationContext, springBean);
        }

        SpringBean springBean = new SpringBean();
        springBean.setBeanName(ruleTemplate.getSpringMethod().getAlwaysBeanName());
        springBean.setMethodName(ruleTemplate.getSpringMethod().getAlwaysMethodName());
        return new SpringAction<U>(applicationContext, springBean);
    }

    @Override
    public String getType() {
        return "SPRING";
    }
}
