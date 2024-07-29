package com.pw.rulesengine.rule;

public interface Condition<T> extends Definition{
    boolean evaluate(T t);
}
