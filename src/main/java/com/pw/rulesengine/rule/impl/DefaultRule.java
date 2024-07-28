package com.pw.rulesengine.rule.impl;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.Rule;

public class DefaultRule<T,U> implements Rule<T,U>{

    private Condition<T> condition;
    private Action<U> action;
    // private String type;

    @Override
    public Condition<T> getCondition() {
        return condition;
    }

    @Override
    public void setCondition(Condition<T> condition) {
        this.condition = condition;
    }

    @Override
    public Action<U> getAction() {
        return action;
    }

    @Override
    public void setAction(Action<U> action) {
        this.action = action;
    }

    // @Override
    // public String getType() {
    //     return type;
    // }

    // @Override
    // public void setType(String type) {
    //     this.type = type;
    // }
}
