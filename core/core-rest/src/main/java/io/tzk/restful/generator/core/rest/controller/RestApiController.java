package io.tzk.restful.generator.core.rest.controller;

import io.tzk.restful.generator.core.api.domain.dto.res.StandardResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author tianzhenkun
 * @since 2023/3/29
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RestApiController {

    @GetMapping("{groupCode}/{apiCode}")
    public ResponseEntity<StandardResult> get(@PathVariable String groupCode,
                                              @PathVariable String apiCode,
                                              @RequestHeader(required = false) String authorization,
                                              @RequestParam Map<String, Object> param) {
        return ResponseEntity.ok(null);
    }

}
