package com.pw.workflowengine.workflow;

import java.util.Map;

public interface Expression<K, V> {

    public  Map<K, V> getEvaluateContext();
    public  void setEvaluateContext(Map<K, V> context);
    public String getEvaluateExpression();
    public void setEvaluateExpression(String evaluateExpression);

    public Map<K, V> getPassContext();
    public void setPassContext(Map<K, V> context);
    public String getPassExpression();
    public void setPassExpression(String passExpression);

    public Map<K, V> getFailContext();
    public void setFailContext(Map<K, V> context);
    public String getFailExpression();
    public void setFailExpression(String failExpression);

    public Map<K, V> getAlwaysContext();
    public void setAlwaysContext(Map<K, V> context);
    public String getAlwaysExpression();
    public void setAlwaysExpression(String alwaysExpression);
}
