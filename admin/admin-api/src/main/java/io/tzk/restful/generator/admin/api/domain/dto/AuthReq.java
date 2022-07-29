package io.tzk.restful.generator.admin.api.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(title = "登录参数")
public record AuthReq(
        @Schema(title = "账户", required = true)
        @NotBlank String username,
        @Schema(title = "密码", required = true)
        @NotBlank String password) {

}
