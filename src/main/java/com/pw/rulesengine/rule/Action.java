package com.pw.rulesengine.rule;

public interface Action<U> extends Definition {
    void execute(U u);
}
