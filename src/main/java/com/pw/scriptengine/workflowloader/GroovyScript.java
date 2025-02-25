package com.pw.scriptengine.workflowloader;

import java.util.Map;

public interface GroovyScript {

    boolean execute(Map<String, Object> context);
}