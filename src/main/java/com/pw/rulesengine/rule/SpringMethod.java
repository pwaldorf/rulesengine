package com.pw.rulesengine.rule;

public interface SpringMethod {

    public String getEvaluateBeanName();
    public void setEvaluateBeanName(String evaluateBeanName);
    public String getEvaluateMethodName();
    public void setEvaluateMethodName(String evaluateMethodName);

    public String getPassBeanName();
    public void setPassBeanName(String passBeanName);
    public String getPassMethodName();
    public void setPassMethodName(String passMethodName);

    public String getFailBeanName();
    public void setFailBeanName(String failBeanName);
    public String getFailMethodName();
    public void setFailMethodName(String failMethodName);

    public String getAlwaysBeanName();
    public void setAlwaysBeanName(String alwaysBeanName);
    public String getAlwaysMethodName();
    public void setAlwaysMethodName(String alwaysMethodName);
}
