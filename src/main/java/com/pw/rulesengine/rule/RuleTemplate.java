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

    public String getPassActionType();
    public void setPassActionType(String passActionType);

    public String getFailActionType();
    public void setFailActionType(String failActionType);

    public String getAlwaysActionType();
    public void setAlwaysActionType(String alwaysActionType);

    public String getStatementType();
    public void setStatementType(String statementType);

    public String getDescription();
    public void setDescription(String description);

    public Expression getExpression();
    public void setExpression(Expression expression);

    public JavaClassName getJavaClassName();
    public void setJavaClassName(JavaClassName javaClassName);

    public SpringMethod getSpringMethod();
    public void setSpringMethod(SpringMethod springMethod);
}
