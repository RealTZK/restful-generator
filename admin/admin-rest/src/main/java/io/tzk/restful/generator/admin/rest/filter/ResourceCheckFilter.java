package io.tzk.restful.generator.admin.rest.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * @author tianzhenkun
 * @since 2023/4/14
 */
@Component
@RequiredArgsConstructor
public class ResourceCheckFilter extends OncePerRequestFilter {

    private final RequestMappingHandlerMapping mapping;
    private final PathMatcher pathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        boolean exists = mapping.getHandlerMethods().keySet().stream()
                .anyMatch(mappingInfo -> mappingInfo.getMethodsCondition()
                        .getMethods()
                        .stream()
                        .map(Enum::name)
                        .anyMatch(method::equals)
                        &&
                        mappingInfo.getPathPatternsCondition()
                                .getPatterns()
                                .stream()
                                .map(PathPattern::getPatternString)
                                .anyMatch(pattern -> pathMatcher.match(pattern, uri)));
        if (!exists) {
            throw new NoSuchElementException("resource [%s] not exists".formatted(uri));
        }
        filterChain.doFilter(request, response);
    }
}
