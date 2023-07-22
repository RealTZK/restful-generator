package io.tzk.restful.generator.admin.rest.controller;

import io.tzk.restful.generator.admin.api.domain.dto.req.UserCReq;
import io.tzk.restful.generator.admin.api.domain.dto.res.UserRes;
import io.tzk.restful.generator.admin.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid UserCReq req) {
        return ResponseEntity.created(URI.create("/user/%d".formatted(userService.create(req)))).build();
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserRes> get(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

}
