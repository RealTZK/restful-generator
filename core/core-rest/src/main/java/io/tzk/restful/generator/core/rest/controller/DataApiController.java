package io.tzk.restful.generator.core.rest.controller;

import io.tzk.restful.generator.core.api.domain.dto.req.ApiConfigCReq;
import io.tzk.restful.generator.core.api.domain.dto.req.DataApiCReq;
import io.tzk.restful.generator.core.api.service.DataApiService;
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
@RequestMapping("apiGroups/{groupId}/dataApis")
@RequiredArgsConstructor
public class DataApiController {

    private final DataApiService dataApiService;

    @PostMapping
    public ResponseEntity<Void> create(@PathVariable Long groupId, @Valid @RequestBody DataApiCReq req) {
        return ResponseEntity.created(URI.create("/apiGroups/%d/dataApis/%d".formatted(groupId, dataApiService.create(groupId, req)))).build();
    }

    @PostMapping("{apiId}/configs")
    public ResponseEntity<Void> addConfig(@PathVariable Long groupId, @PathVariable Long apiId, @Valid @RequestBody ApiConfigCReq req) {
        req.configType().validateConfig(req.params());
        dataApiService.addConfig(groupId, apiId, req);
        return null;
    }
}
