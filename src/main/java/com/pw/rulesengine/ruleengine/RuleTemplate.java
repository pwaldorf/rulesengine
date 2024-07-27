package com.pw.rulesengine.ruleengine;

import com.pw.rulesengine.models.DefaultExpression;
import com.pw.rulesengine.models.DefaultSpringMethod;

public interface RuleTemplate {
    public String getRulesetName();
    public void setRulesetName(String rulesetName);

    public String getRuleId();
    public void setRuleId(String ruleId);

    public Integer getPriority();
    public void setPriority(Integer priority);

    public String getType();
    public void setType(String type);

    public String getDescription();
    public void setDescription(String description);

    public DefaultExpression getExpression();
    public void setExpression(DefaultExpression expression);

    public String getClassName();
    public void setClassName(String className);

    public DefaultSpringMethod getSpringMethod();
    public void setSpringMethod(DefaultSpringMethod springMethod);
}
