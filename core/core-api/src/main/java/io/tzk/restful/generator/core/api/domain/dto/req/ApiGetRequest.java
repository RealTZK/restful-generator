package io.tzk.restful.generator.core.api.domain.dto.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author tianzhenkun
 * @since 2023/4/14
 */
public record ApiGetRequest(
        @NotBlank(message = "request can't be null")
        String requestId,
        Boolean debug,
        @Min(value = 1, message = "page num must greater with 1")
        Integer pageNum,
        @Min(value = 1, message = "page size must greater than 1")
        Integer pageSize,
        @RequestParam Map<String, Object> param) {
}
