package com.pw.scriptengine.scriptloader;

import com.pw.scriptengine.scriptloader.db.ScriptTemplate;
import com.pw.scriptengine.scriptloader.db.ScriptTemplateService;
import groovy.lang.GroovyClassLoader;
import jakarta.annotation.PostConstruct;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

@Component
public class GwhScriptLoader {

    private final ScriptTemplateService scriptTemplateService;
    private final ConfigurableApplicationContext applicationContext;

    private final GroovyClassLoader groovyClassLoader;

    private final ConcurrentHashMap<String, Class<?>> compiledClasses = new ConcurrentHashMap<>();
    private final ContentNegotiatingViewResolver contentNegotiatingViewResolver;

    public GwhScriptLoader(ScriptTemplateService  scriptTemplateService, GenericApplicationContext applicationContext, ContentNegotiatingViewResolver contentNegotiatingViewResolver) {
        this.scriptTemplateService = scriptTemplateService;
        this.applicationContext = applicationContext;

        CompilerConfiguration config = new CompilerConfiguration();
        config.setScriptBaseClass(GroovyScript.class.getName());
        config.setTargetBytecode("17");
        groovyClassLoader = new GroovyClassLoader(this.getClass().getClassLoader(), config);
        this.contentNegotiatingViewResolver = contentNegotiatingViewResolver;
    }

    @PostConstruct
    public void init() {
        loadScript("TestProcess");
    }

    public void loadScript(String profileName) {

        List<ScriptTemplate> scriptTemplates = scriptTemplateService.getScriptTemplates(profileName);
        if (scriptTemplates.isEmpty()) {
            throw new IllegalArgumentException("No script templates found for profile: " + profileName);
        }

        try {
            for (ScriptTemplate scriptTemplate : scriptTemplates) {
                Class<?> compiledClass = loadAndCache(scriptTemplate.getScriptBody(), scriptTemplate.getScriptName());
                DynamicScriptRegistrar.registerGroovyBean(compiledClass, scriptTemplate.getScriptName(), applicationContext);
            }

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