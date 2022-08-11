package io.tzk.restful.generator.admin.rest.controller;

import io.tzk.restful.generator.admin.api.domain.dto.req.UserCReq;
import io.tzk.restful.generator.admin.api.domain.dto.res.UserRes;
import io.tzk.restful.generator.admin.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('POST:/user')")
    public ResponseEntity<Long> create(@RequestBody @Valid UserCReq condition) {
        return ResponseEntity.ok(userService.save(condition));
    }

    @GetMapping("{userId}")
    @PreAuthorize("hasAuthority('GET:/user/{userId}')")
    public ResponseEntity<UserRes> get(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }

}
