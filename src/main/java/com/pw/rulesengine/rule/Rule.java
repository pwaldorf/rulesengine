package com.pw.rulesengine.rule;

public interface Rule<T, U> { //extends Condition<T>, Action<U> {
    // String getType();
    // void setType(String type);
    public Condition<T> getCondition();
    void setCondition(Condition<T> condition);
    public Action<U> getAction();
    void setAction(Action<U> action);

    // boolean evaluate(T o);
    // default void pass(U o) {};
    // default void fail(U o) {};
    // default void always(U o) {};
}
