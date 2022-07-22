package io.tzk.restful.generator.tenant.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Tag(name = "会话接口")
public class SessionController {

    @Resource
    private OAuth2AuthorizedClientService authorizedClientService;

    @PostMapping("/session")
    @Operation(summary = "登录")
    public ResponseEntity<String> login(OAuth2AuthenticationToken authentication) {
        return ResponseEntity.ok(authentication.getName());
    }

    @DeleteMapping("/session")
    @Operation(summary = "登出")
    public ResponseEntity<?> logout(Authentication authentication) {
        authentication.setAuthenticated(false);
        return ResponseEntity.ok("成功");
    }
}
