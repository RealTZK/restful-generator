package io.tzk.restful.generator.admin.rest.controller;

import io.tzk.restful.generator.admin.api.domain.dto.req.AuthReq;
import io.tzk.restful.generator.admin.api.service.TokenService;
import io.tzk.restful.generator.admin.rest.util.JwtUtil;
import io.tzk.restful.generator.admin.rest.util.TokenConverter;
import io.tzk.restful.generator.common.util.serialize.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

import static io.tzk.restful.generator.admin.rest.util.JwtUtil.TOKEN_PREFIX;

@RequiredArgsConstructor
@RestController
@RequestMapping("token")
public class TokenController {

    private final TokenService tokenService;

    private final JSON serializer;

    private final TokenConverter tokenConverter;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid AuthReq req, HttpServletResponse response) {
        Optional.of(tokenService.login(req))
                .map(tokenConverter::convert)
                .map(serializer::serialize)
                .map(JwtUtil::createJWT)
                .ifPresent(jwt -> response.setHeader(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + jwt));
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(HttpStatus.CREATED.getReasonPhrase());
    }

}
