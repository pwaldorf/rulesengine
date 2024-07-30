package com.pw.rulesengine.rule.builder;

public interface Builder<T, U> {
    T build(U u);
}
