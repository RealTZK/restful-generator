package io.tzk.restful.generator.admin.api.domain.dto.req;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(title = "user create param")
public record UserCReq(
        @NotNull(message = "username can't be null")
        @Size(min = 1, max = 20, message = "username too long or too short")
        @Schema(required = true)
        String username,

        @NotNull(message = "username can't be null")
        @Size(min = 1, max = 20, message = "password too long or too short")
        @Schema(required = true)
        String password,

        @Size(min = 1, max = 20, message = "nickName too long or too short")
        String nickName,

        @Email
        String email) {

}
