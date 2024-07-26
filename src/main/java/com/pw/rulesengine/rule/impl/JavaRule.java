package com.pw.rulesengine.rule.impl;

import com.pw.rulesengine.rule.Rule;

// @SuppressWarnings("rawtypes")
public interface JavaRule<T, U> extends Rule<T, U> {

    default String getType() {
        return "JAVA";
    }

}
