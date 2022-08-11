package io.tzk.restful.generator.core.api.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import io.tzk.restful.generator.core.api.enums.SourceType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(title = "data source create param")
public record SourceCReq(
        @NotBlank
        @Schema(required = true)
        String name,

        @NotNull
        SourceType type,

        @NotBlank
        String url,

        String username,

        String password,

        String driver,

        @NotNull
        Long connectorId) {
}
