package com.pw.rulesengine.rule;

public interface Rule<T, U> {

    public Condition<T> getCondition();
    void setCondition(Condition<T> condition);

    public Action<U> getAction();
    void setAction(Action<U> action);
}
