package com.pw.rulesengine.ruleengine;

public interface RuleEngine<T, U> {
    void run(T t, U u);
}
