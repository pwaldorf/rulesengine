package com.pw.scriptengine.scriptloader;

import java.util.Map;

public interface GroovyScript {

    void execute(Map<String, Object> context);
}