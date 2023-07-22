package io.tzk.restful.generator.core.rest.controller;

import io.tzk.restful.generator.core.api.domain.dto.req.DataSourceCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.SourceRes;
import io.tzk.restful.generator.core.api.service.DataSourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dataSources")
@RequiredArgsConstructor
public class DataSourceController {

    private final DataSourceService dataSourceService;

    @GetMapping("{sourceId}")
    public ResponseEntity<SourceRes> get(@PathVariable Long sourceId) {
        return ResponseEntity.ok(dataSourceService.get(sourceId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody DataSourceCReq req) {
        return null;
    }

}
