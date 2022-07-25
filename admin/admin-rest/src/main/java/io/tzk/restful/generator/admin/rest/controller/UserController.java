package io.tzk.restful.generator.admin.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.tzk.restful.generator.admin.api.service.UserService;
import io.tzk.restful.generator.admin.api.domain.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/user")
    @Operation(summary = "创建用户")
    public ResponseEntity<Long> create(User condition) {
        return ResponseEntity.ok(userService.save(condition));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "查看用户")
    public ResponseEntity<User> get(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }

}
