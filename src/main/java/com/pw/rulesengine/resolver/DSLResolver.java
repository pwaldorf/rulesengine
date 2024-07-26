package com.pw.rulesengine.resolver;

public interface DSLResolver {
    String getResolverKeyword();
    Object resolveValue(String keyword);
}
