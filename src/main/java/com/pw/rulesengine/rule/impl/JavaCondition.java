package com.pw.rulesengine.rule.impl;

import com.pw.rulesengine.rule.Condition;

public interface JavaCondition<T> extends Condition<T> {

    default String getType() {
        return "JAVA";
    }

}
