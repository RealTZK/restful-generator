package io.tzk.restful.generator.tenant.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "会话接口")
public class SessionController {

    @PostMapping("/session")
    @Operation(summary = "登录")
    public ResponseEntity<String> login() {
        var context = SecurityContextHolder.getContext();
        var authentication = context.getAuthentication();
        return ResponseEntity.ok(authentication.getName());
    }

    @DeleteMapping("/session")
    @Operation(summary = "登出")
    public ResponseEntity<?> logout(Authentication authentication) {
        authentication.setAuthenticated(false);
        return ResponseEntity.ok("成功");
    }
}
