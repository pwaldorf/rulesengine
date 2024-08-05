package com.pw.rulesengine.rule;

import java.util.Map;

public interface Expression {

    public Map<String, Object> getEvaluateContext();
    public void setEvaluateContext(Map<String, Object> context);
    public String getEvaluateExpression();
    public void setEvaluateExpression(String evaluateExpression);

    public Map<String, Object> getPassContext();
    public void setPassContext(Map<String, Object> context);
    public String getPassExpression();
    public void setPassExpression(String passExpression);

    public Map<String, Object> getFailContext();
    public void setFailContext(Map<String, Object> context);
    public String getFailExpression();
    public void setFailExpression(String failExpression);

    public Map<String, Object> getAlwaysContext();
    public void setAlwaysContext(Map<String, Object> context);
    public String getAlwaysExpression();
    public void setAlwaysExpression(String alwaysExpression);
}
