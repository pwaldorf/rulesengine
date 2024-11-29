package com.pw.workflowengine.workflow;

import java.util.Map;

public interface GroovyScript {

    boolean execute(Map<String, Object> context);
}
