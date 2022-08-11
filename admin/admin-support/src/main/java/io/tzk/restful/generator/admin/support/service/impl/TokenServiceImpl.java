package io.tzk.restful.generator.admin.support.service.impl;

import io.tzk.restful.generator.admin.api.domain.dto.req.AuthReq;
import io.tzk.restful.generator.admin.api.domain.entity.User;
import io.tzk.restful.generator.admin.api.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final AuthenticationManager authenticationManager;

    @Override
    public UserDetails login(AuthReq req) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(req.username(), req.password());
        return Optional.ofNullable(authenticationManager.authenticate(authentication))
                .map(auth -> (User) auth.getPrincipal())
                .orElseThrow(() -> new AuthenticationServiceException("login failed"));
    }
}
