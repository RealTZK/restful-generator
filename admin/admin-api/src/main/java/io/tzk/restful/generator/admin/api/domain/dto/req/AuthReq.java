package io.tzk.restful.generator.admin.api.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

@Schema(title = "login param")
public record AuthReq(
        @Schema(required = true)
        @NotBlank String username,
        @Schema(required = true)
        @NotBlank String password) {

}
