package com.pw.rulesengine.rule;

import com.pw.rulesengine.ruleengine.Statement;

public interface Rule<T, U> {

    public Condition<T> getCondition();
    void setCondition(Condition<T> condition);
    public Statement getStatement();
    void setStatement(Statement statement);

    default Action<U> getPassAction() {return null;};
    default Action<U> getFailAction() {return null;};
    default Action<U> getAlwaysAction() {return null;};
}
