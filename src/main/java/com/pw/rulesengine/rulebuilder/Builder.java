package com.pw.rulesengine.rulebuilder;

public interface Builder<T, U> {
    T build(U u);
}
