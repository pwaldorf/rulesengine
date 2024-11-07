package com.pw.workflowengine.workflow.impl;

// import com.pw.workflowengine.stepengine.Statement;
import com.pw.workflowengine.workflow.Action;
import com.pw.workflowengine.workflow.Condition;
import com.pw.workflowengine.workflow.Step;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultStep<K, V> implements Step<K, V>{

    private Condition<K, V> condition;
    // private Statement statement;

    private Action<K, V> passAction;
    private Action<K, V> failAction;
    private Action<K, V> AlwaysAction;

    private boolean breakOnPass;
    private boolean breakOnFail;
}
