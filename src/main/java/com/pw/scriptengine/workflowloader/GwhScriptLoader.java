package com.pw.scriptengine.workflowloader;

import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

public class GwhScriptLoader {

    private GroovyClassLoader groovyClassLoader;

    private ConcurrentHashMap<String, Class<?>> compiledClasses = new ConcurrentHashMap<>();

    public GwhScriptLoader() {
        CompilerConfiguration config = new CompilerConfiguration();
        config.setScriptBaseClass(GroovyScript.class.getName());
        config.setTargetBytecode("17");
        groovyClassLoader = new GroovyClassLoader(this.getClass().getClassLoader(), config);
    }

    public GroovyScript loadScript(List<GwhScriptTemplate> gwhScriptTemplates) {
        try {
            Class<?> compiledClass = null;
            for (GwhScriptTemplate gwhScriptTemplate : gwhScriptTemplates) {
                compiledClass = loadAndCache(gwhScriptTemplate.getScriptBody(), gwhScriptTemplate.getScriptClassName());
            }

            assert compiledClass != null;
            return  (GroovyScript) compiledClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading Groovy script", e);
        }
    }

    private Class<?> loadAndCache(String script, String className) {
        return compiledClasses.computeIfAbsent(className, key -> {
            try {
                return groovyClassLoader.parseClass(script);
            } catch (Exception e) {
                throw new RuntimeException("Error compiling Groovy script: " + className, e);
            }
        });
    }
}