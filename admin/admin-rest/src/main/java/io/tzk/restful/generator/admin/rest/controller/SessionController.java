package io.tzk.restful.generator.admin.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.tzk.restful.generator.admin.api.domain.dto.AuthReq;
import io.tzk.restful.generator.admin.api.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController("session")
@Tag(name = "会话接口")
@RequiredArgsConstructor
public class SessionController {

    private final AuthenticationManager authenticationManager;

    private final JwtEncoder jwtEncoder;

    @GetMapping
    public ResponseEntity<String> login(@Valid AuthReq req) {
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(req.account(), req.password()));

        var user = (User) authentication.getPrincipal();

        Instant now = Instant.now();
        long expiry = 36000L;

        var scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("restful.generator")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(format("%s,%s", user.getId(), user.getUsername()))
                .claim("roles", scope)
                .build();

        var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

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
