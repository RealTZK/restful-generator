package io.tzk.restful.generator.core.rest.controller;

import io.tzk.restful.generator.core.api.domain.dto.res.SourceRes;
import io.tzk.restful.generator.core.api.service.SourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("source")
@RequiredArgsConstructor
public class SourceController {

    private final SourceService sourceService;

    @GetMapping("{sourceId}")
    public ResponseEntity<SourceRes> get(@PathVariable Long sourceId) {
        return ResponseEntity.ok(sourceService.get(sourceId));
    }

}
