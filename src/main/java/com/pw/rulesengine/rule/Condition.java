package com.pw.rulesengine.rule;

public interface Condition<T> {
    String getConditionType();
    boolean evaluate(T o);
}
