package com.pw.rulesengine.ruleengine;

public interface Statement<V, W> {
    String getType();
    void execute(V v, W w);
}
