package io.tzk.restful.generator.admin.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.tzk.restful.generator.admin.api.domain.dto.AuthReq;
import io.tzk.restful.generator.admin.api.domain.entity.User;
import io.tzk.restful.generator.admin.rest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("session")
@Tag(name = "会话接口")
@RequiredArgsConstructor
public class SessionController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public ResponseEntity<String> login(@Valid AuthReq req) {
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(req.account(), req.password()));

        var user = (User) authentication.getPrincipal();

        var token = jwtTokenUtil.generateAccessToken(user);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .body("SUCCESS");
    }

    @PostMapping
    public String post() {
        return "post";
    }

    @DeleteMapping
    public String delete() {
        return "delete";
    }

}
