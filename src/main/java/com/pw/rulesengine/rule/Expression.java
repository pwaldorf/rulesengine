package com.pw.rulesengine.rule;

import java.util.Map;

public interface Expression {

    public String getEvaluateExpression();
    public void setEvaluateExpression(String evaluateExpression);
    public String getPassExpression();
    public void setPassExpression(String passExpression);
    public String getFailExpression();
    public void setFailExpression(String failExpression);
    public String getAlwaysExpression();
    public void setAlwaysExpression(String alwaysExpression);
    public Map<String, Object> getContext();
    public void setContext(Map<String, Object> context);
}
