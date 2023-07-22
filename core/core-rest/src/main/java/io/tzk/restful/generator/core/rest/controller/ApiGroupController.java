package io.tzk.restful.generator.core.rest.controller;

import io.tzk.restful.generator.core.api.domain.dto.req.ApiGroupCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.ApiGroupRes;
import io.tzk.restful.generator.core.api.service.ApiGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author tianzhenkun
 * @since 2023/4/12
 */
@RestController
@RequestMapping("apiGroups")
@RequiredArgsConstructor
public class ApiGroupController {

    private final ApiGroupService apiGroupService;

    @GetMapping("{groupId}")
    public ResponseEntity<ApiGroupRes> get(@PathVariable Long groupId) {
        return ResponseEntity.ok(apiGroupService.get(groupId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ApiGroupCReq req) {
        return ResponseEntity.created(URI.create("/apiGroup/%d".formatted(apiGroupService.create(req)))).build();
    }
}
