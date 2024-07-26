package com.pw.rulesengine.rule;

public interface Rule<T, U> {
    String getType();
    boolean evaluate(T o);
    void execute(U o);
}
