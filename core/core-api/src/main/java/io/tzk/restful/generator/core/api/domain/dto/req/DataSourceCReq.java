package io.tzk.restful.generator.core.api.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import io.tzk.restful.generator.core.api.enums.DataSourceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(title = "data source create param")
public record DataSourceCReq(
        @NotBlank(message = "data source name can't be blank")
        String name,

        @NotNull(message = "data source type can't be null")
        DataSourceType type,

        @NotBlank(message = "data source url can't be null")
        String url,

        String username,

        String password) {
}
