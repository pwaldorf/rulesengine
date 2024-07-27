package com.pw.rulesengine.rule;

public interface Rule<T, U> {
    String getType();
    boolean evaluate(T o);
    default void pass(U o) {};
    default void fail(U o) {};
    default void always(U o) {};
}
