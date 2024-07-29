package com.pw.rulesengine.rule.builder;

public interface Builder<T, U, V> {
    T build(U u, V v);
}
