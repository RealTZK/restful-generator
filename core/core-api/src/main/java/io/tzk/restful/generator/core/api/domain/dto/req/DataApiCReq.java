package io.tzk.restful.generator.core.api.domain.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author tianzhenkun
 * @since 2023/4/12
 */
public record DataApiCReq(
        @NotBlank(message = "api code can't be blank")
        String apiCode,
        @NotBlank(message = "api name can't be blank")
        String apiName,
        String description,
        @NotNull(message = "source can't be null")
        Long sourceId,
        String sentence) {
}
