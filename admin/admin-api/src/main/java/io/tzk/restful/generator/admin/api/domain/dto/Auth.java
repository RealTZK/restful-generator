package io.tzk.restful.generator.admin.api.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Schema(title = "登录参数")
@Data
public class Auth {


    @Schema(title = "账户", required = true)
    @NotBlank
    private String username;
    @Schema(title = "密码", required = true)
    @NotBlank
    private String password;



}
