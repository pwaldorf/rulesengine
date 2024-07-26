package com.pw.rulesengine.ruleengine;

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

    public String getCondition();
    public void setCondition(String condition);

    public String getAction();
    public void setAction(String action);

    public String getClassName();
    public void setClassName(String className);

    public String getEvaluateBeanName();
    public void setEvaluateBeanName(String evaluateBeanName);

    public String getEvaluateMethodName();
    public void setEvaluateMethodName(String evaluateMethodName);

    public String getExecuteBeanName();
    public void setExecuteBeanName(String executeBeanName);

    public String getExecuteMethodName();
    public void setExecuteMethodName(String executeMethodName);
}
