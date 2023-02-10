package io.tzk.restful.generator.admin.rest.filter;

import io.tzk.restful.generator.admin.api.domain.dto.TokenBody;
import io.tzk.restful.generator.admin.rest.util.JwtUtil;
import io.tzk.restful.generator.admin.rest.util.TokenConverter;
import io.tzk.restful.generator.common.util.serialize.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static io.tzk.restful.generator.admin.rest.util.JwtUtil.TOKEN_PREFIX;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JSON serializer;

    private final TokenConverter tokenConverter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get token and put it into SecurityContext
        Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .map(token -> token.replaceAll(TOKEN_PREFIX, ""))
                .map(token -> JwtUtil.parseJWT(token).getSubject())
                .map(subject -> serializer.deserialize(subject, TokenBody.class))
                .map(tokenConverter::convert)
                .ifPresent(user -> {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });

        filterChain.doFilter(request, response);
    }
}
