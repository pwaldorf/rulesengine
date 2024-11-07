package com.pw.workflowengine.workflow;

public interface StepTemplate<K, V> {

    String getProcessName();

    String getStepGroup();

    Integer getOrder();

    String getStepRuleType();

    String getStepPassType();

    String getStepFailType();

    String getStepAlwaysType();

    String getDescription();

    boolean isBreakOnPass();

    boolean isBreakOnFail();

    Expression<K, V> getExpression();

    JavaClassName getJavaClassName();

    SpringMethod getSpringMethod();

    void setProcessName(String processName);

    void setStepGroup(String stepGroup);

    void setOrder(Integer order);

    void setStepRuleType(String stepRuleType);

    void setStepPassType(String stepPassType);

    void setStepFailType(String stepFailType);

    void setStepAlwaysType(String stepAlwaysType);

    void setDescription(String description);

    void setBreakOnPass(boolean breakOnPass);

    void setBreakOnFail(boolean breakOnFail);

    void setExpression(Expression<K, V> expression);

    void setJavaClassName(JavaClassName javaClassName);

    void setSpringMethod(SpringMethod springMethod);
}
