package com.pw.scriptengine.scriptloader.db;

import groovy.lang.GroovyClassLoader;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class ApplicationPreparedListener implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        System.out.println("Handling ApplicationPreparedEvent - Loading Groovy beans");

        ConfigurableApplicationContext context = event.getApplicationContext();
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) context.getBeanFactory();

        // 1. Load scripts from DB or wherever (hardcoded here for demo)
        List<GroovyScriptInfo> scriptList = loadGroovyScriptsFromDb(); // implement this method

        // 2. Create GroovyClassLoader
        try (GroovyClassLoader groovyClassLoader = new GroovyClassLoader(this.getClass().getClassLoader())) {
            for (GroovyScriptInfo scriptInfo : scriptList) {
                Class<?> groovyClass = groovyClassLoader.parseClass(scriptInfo.scriptBody());

                // 3. Register dynamically
                GenericBeanDefinition bd = new GenericBeanDefinition();
                bd.setBeanClass(groovyClass);
                bd.setScope(BeanDefinition.SCOPE_SINGLETON);
                bd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
                bd.setPrimary(true); // to override existing beans if needed

                String beanName = scriptInfo.beanName(); // must be unique or match to override
                if (registry.containsBeanDefinition(beanName)) {
                    registry.removeBeanDefinition(beanName);
                }
                registry.registerBeanDefinition(beanName, bd);
                System.out.println("Registered Groovy bean: " + beanName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load groovy scripts", e);
        }
    }

    // Placeholder: Replace with Resource. Just like applciation properties, get resource class via reflection
    private List<GroovyScriptInfo> loadGroovyScriptsFromDb() {
        return List.of(
                new GroovyScriptInfo("mySampleGroovyBImpl", """
                         package com.pw.action
                        
                         import com.pw.scriptengine.scriptloader.GroovyScript
                         import groovy.transform.CompileStatic
                         import org.springframework.stereotype.Component
                    
                         @CompileStatic
                         @Component
                         class MySampleGroovyBImpl implements MySampleGroovyB {
                    
                             @Override
                             void execute(Map<String, Object> context) {
                                 setSystemId(context)
                                 setSwiftBic(context)
                             }
                    
                             void setSystemId(Map<String, Object> context) {
                                 String groupKey = (String) context.get("GroupKey")
                                 if (groupKey != null) {
                                     context.put("SourceSystemId", groupKey)
                                 }
                             }
                    
                             void setSwiftBic(Map<String, Object> context) {
                    
                                 String senderBic = (String) context.get("SenderBic")
//                                 System.out.println("PJW IN Script")
                                 if (senderBic != null) {
                                     context.put("SwiftBic", senderBic)
                                     context.put("SsbOfficeCodeMap", senderBic)
                                 }
                             }
                    
                         }
                    """),
                new GroovyScriptInfo("mySampleBeanCImpl", """
                         package com.pw.scriptengine.bean;

                          import org.springframework.stereotype.Component;
                          import groovy.transform.CompileStatic
                    
                          import java.util.Map;
                    
                          @CompileStatic
                          @Component
                          public class MySampleBeanCImpl implements MySampleBeanC{
                    
                              @Override
                              public void execute(Map<String, Object> context) {
                                  setSystemId(context)
                              }
                    
                              private void setSystemId(Map<String, Object> context) {
                                  System.out.println("pjw in java script")
                                  String groupKey = (String) context.get("GroupKey")
                                  if (groupKey != null) {
                                      context.put("SourceSystemId", groupKey)
                                  }
                              }
                          }
                    """),
                new GroovyScriptInfo("groovyScriptProcessorCImpl", """
                        package com.pw.groovy.actions

                        import com.pw.scriptengine.scriptloader.GroovyScript
                        import groovy.transform.CompileStatic
                        import org.springframework.stereotype.Component
                        import com.pw.action.GroovyScriptProcessorC

                        @CompileStatic
                        @Component
                        class GroovyScriptProcessorCImpl implements GroovyScriptProcessorC {
            
                            @Override
                            void execute(Map<String, Object> context) {
                                setSystemId(context)
                                setSwiftBic(context)
                            }
            
                            void setSystemId(Map<String, Object> context) {
                                String groupKey = (String) context.get("GroupKey")
                                if (groupKey != null) {
                                    context.put("SourceSystemId", groupKey)
                                }
                            }
            
                            void setSwiftBic(Map<String, Object> context) {
            
                                String senderBic = (String) context.get("SenderBic")
                                if (senderBic != null) {
                                    context.put("SwiftBic", senderBic)
                                    context.put("SsbOfficeCodeMap", senderBic)
                                }
                            }
                        }
                    """),
                new GroovyScriptInfo("groovyScriptProcessorA", """
                        package com.pw.groovy.actions

                        import com.pw.scriptengine.scriptloader.GroovyScript
                        import groovy.transform.CompileStatic
                        import org.springframework.stereotype.Component
                        import com.pw.action.GroovyScriptProcessorC
                
                        @CompileStatic
                        @Component
                        class GroovyScriptProcessorA implements GroovyScript {
                             private final GroovyScriptProcessorC actionb
                
                             GroovyScriptProcessorA(GroovyScriptProcessorC actionb) {
                                 this.actionb = actionb
                             }
                
                             GroovyScriptProcessorA() {
                
                              }
                
                             void execute(Map<String, Object> context) {
                                String sourceSystemName = (String) context.get("SourceSystemName")
                                if ("GTM".equalsIgnoreCase(sourceSystemName)) {
                                    String buyCurrency = (String) context.get("BuyCurrency")
                                    String sellCurrency = (String) context.get("SellCurrency")
                
                                    if ("CLS".equalsIgnoreCase(buyCurrency) || "CLS".equalsIgnoreCase(sellCurrency)) {
                                        setStatusCode(context)
                
                                        if ("SOKS".equalsIgnoreCase((String) context.get("StatusCode"))) {
                                            actionb.execute(context)
                                        }
                                    } else {
                                        context.put("Status", "IGNORE")
                                        context.put("ReasonCode", "Status code " + context.get("StatusCode") + " can be ignored")
                                    }
                                }
                            }
                
                            void setStatusCode(Map<String, Object> context) {
                                String statusCode = (String) context.get("StatusCode")
                                if (statusCode != null && statusCode.endsWith("/")) {
                                    statusCode = statusCode.substring(0, statusCode.length() - 1)
                                    context.put("StatusCode", statusCode)
                                }
                            }
                        }
                    """)
        );
    }

    // DTO for script info
    private record GroovyScriptInfo(String beanName, String scriptBody) {}
}