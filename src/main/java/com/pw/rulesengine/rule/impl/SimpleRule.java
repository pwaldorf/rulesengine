package com.pw.rulesengine.rule.impl;

import com.pw.rulesengine.rule.Action;
import com.pw.rulesengine.rule.Condition;
import com.pw.rulesengine.rule.Rule;
import com.pw.rulesengine.ruleengine.Statement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleRule<T, U> implements Rule<T, U>{

    private Condition<T> condition;
    private Action<U> passAction;
    private Statement statement;
}
