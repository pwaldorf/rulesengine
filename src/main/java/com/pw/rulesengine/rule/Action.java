package com.pw.rulesengine.rule;

public interface Action<U> {
    String getActionType();
    default void pass(U o) {};
    default void fail(U o) {};
    default void always(U o) {};
}
