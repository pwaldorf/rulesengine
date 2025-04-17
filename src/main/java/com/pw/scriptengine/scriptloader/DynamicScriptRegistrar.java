package com.pw.scriptengine.scriptloader;

import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class DynamicScriptRegistrar {

    public static void registerGroovyBean(Class<?> clazz, String beanName, ApplicationContext context) {
        if (!(context instanceof GenericApplicationContext gac)) {
            throw new IllegalStateException("ApplicationContext must be GenericApplicationContext");
        }

        BeanDefinitionRegistry registry = gac;

        if (!registry.containsBeanDefinition(beanName)) {
            GenericBeanDefinition bd = new GenericBeanDefinition();
            bd.setBeanClass(clazz);
            bd.setAutowireMode(GenericBeanDefinition.AUTOWIRE_CONSTRUCTOR);
            bd.setScope(BeanDefinition.SCOPE_SINGLETON);
            registry.registerBeanDefinition(beanName, bd);
        }
    }
}