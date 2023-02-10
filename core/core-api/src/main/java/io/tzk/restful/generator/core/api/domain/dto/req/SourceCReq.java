package io.tzk.restful.generator.core.api.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import io.tzk.restful.generator.core.api.enums.Connector;
import io.tzk.restful.generator.core.api.enums.SourceType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
        Connector connector) {
}
