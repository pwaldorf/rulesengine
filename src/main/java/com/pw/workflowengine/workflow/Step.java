package com.pw.workflowengine.workflow;

// import com.pw.workflowengine.stepengine.Statement;

public interface Step<K, V> {

    public Condition<K, V> getCondition();
    void setCondition(Condition<K, V> condition);

    default Action<K, V> getPassAction() {return null;};
    default Action<K, V> getFailAction() {return null;};
    default Action<K, V> getAlwaysAction() {return null;};

    default boolean isBreakOnPass() {return false;};
    default boolean isBreakOnFail() {return false;};
}
