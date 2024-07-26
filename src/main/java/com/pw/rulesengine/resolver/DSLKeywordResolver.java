package com.pw.rulesengine.resolver;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
public class DSLKeywordResolver {
    private Map<String, DSLResolver> dslKeywordResolverList;

    public DSLKeywordResolver(List<DSLResolver> resolverList) {
        dslKeywordResolverList = resolverList.stream()
                .collect(Collectors.toMap(DSLResolver::getResolverKeyword, Function.identity()));
    }

    public Map<String, DSLResolver> getDslKeywordResolverList(){
        return dslKeywordResolverList;
    }

    public Optional<DSLResolver> getResolver(String keyword) {
        return Optional.ofNullable(dslKeywordResolverList.get(keyword));
    }
}