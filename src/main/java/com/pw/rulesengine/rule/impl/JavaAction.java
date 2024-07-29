package com.pw.rulesengine.rule.impl;

import com.pw.rulesengine.rule.Action;

public interface JavaAction<T> extends Action<T> {

    default String getType() {
        return "JAVA";
    }

}