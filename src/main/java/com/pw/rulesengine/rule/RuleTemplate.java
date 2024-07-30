package com.pw.rulesengine.rule;

public interface RuleTemplate {

    public String getRulesetName();

    public void setRulesetName(String rulesetName);

    public String getRuleId();

    public void setRuleId(String ruleId);

    public Integer getPriority();

    public void setPriority(Integer priority);

    public String getConditionType();

    public void setConditionType(String conditionType);

    public String getActionType();

    public void setActionType(String actionType);

    public String getDescription();

    public void setDescription(String description);

    public Expression getExpression();

    public void setExpression(Expression expression);

    public String getConditionClassName();

    public void setConditionClassName(String conditionClassName);

    public String getActionClassName();

    public void setActionClassName(String actionClassName);

    public SpringMethod getSpringMethod();

    public void setSpringMethod(SpringMethod springMethod);
}
