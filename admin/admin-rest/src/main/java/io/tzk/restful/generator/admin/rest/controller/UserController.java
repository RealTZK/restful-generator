package io.tzk.restful.generator.admin.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.tzk.restful.generator.admin.api.domain.entity.User;
import io.tzk.restful.generator.admin.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/user")
    @Operation(summary = "创建用户")
    public ResponseEntity<Long> create(User condition) {
        condition.setPassword(passwordEncoder.encode(condition.getPassword()));
        return ResponseEntity.ok(userService.save(condition));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "查看用户")
    public ResponseEntity<User> get(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }

}
