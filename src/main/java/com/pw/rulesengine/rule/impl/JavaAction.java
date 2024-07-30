package com.pw.rulesengine.rule.impl;

import com.pw.rulesengine.rule.Action;

public interface JavaAction<U> extends Action<U> {

    @Override
    default public String getType() {
         return "JAVA";
    }
}