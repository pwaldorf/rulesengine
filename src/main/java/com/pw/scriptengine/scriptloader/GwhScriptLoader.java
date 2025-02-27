package com.pw.scriptengine.scriptloader;

import com.pw.scriptengine.scriptloader.db.ScriptTemplate;
import com.pw.scriptengine.scriptloader.db.ScriptTemplateService;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

@Component
public class GwhScriptLoader {

    private final ScriptTemplateService scriptTemplateService;

    private final GroovyClassLoader groovyClassLoader;

    private final ConcurrentHashMap<String, Class<?>> compiledClasses = new ConcurrentHashMap<>();

    public GwhScriptLoader(ScriptTemplateService  scriptTemplateService) {
        this.scriptTemplateService = scriptTemplateService;

        CompilerConfiguration config = new CompilerConfiguration();
        config.setScriptBaseClass(GroovyScript.class.getName());
        config.setTargetBytecode("17");
        groovyClassLoader = new GroovyClassLoader(this.getClass().getClassLoader(), config);
    }

    public GroovyScript loadScript(String profileName) {

        List<ScriptTemplate> scriptTemplates = scriptTemplateService.getScriptTemplates(profileName);
        if (scriptTemplates.isEmpty()) {
            throw new IllegalArgumentException("No script templates found for profile: " + profileName);
        }

        try {
            Class<?> compiledClass = null;
            for (ScriptTemplate scriptTemplate : scriptTemplates) {
                compiledClass = loadAndCache(scriptTemplate.getScriptBody(), scriptTemplate.getScriptName());
            }

            return  (GroovyScript) compiledClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
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

    public void clearCache() {
        compiledClasses.clear();
    }

    public boolean removeScript(String className) {
        if (className == null || className.trim().isEmpty()) {
            return false;
        }
        return compiledClasses.remove(className) != null;
    }

    public int removeScripts(Set<String> classNames) {
        if (classNames == null || classNames.isEmpty()) {
            return 0;
        }

        int removedCount = 0;
        for (String className : classNames) {
            if (removeScript(className)) {
                removedCount++;
            }
        }
        return removedCount;
    }


    public int removeScriptsByProfile(String profileName) {
        if (profileName == null || profileName.trim().isEmpty()) {
            return 0;
        }

        List<ScriptTemplate> scriptTemplates = scriptTemplateService.getScriptTemplates(profileName);
        return removeScripts(scriptTemplates.stream()
                .map(ScriptTemplate::getScriptName)
                .collect(java.util.stream.Collectors.toSet()));
    }

    public int getCacheSize() {
        return compiledClasses.size();
    }
}