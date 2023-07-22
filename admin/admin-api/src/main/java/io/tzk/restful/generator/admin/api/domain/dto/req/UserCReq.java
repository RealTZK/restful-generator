package io.tzk.restful.generator.admin.api.domain.dto.req;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(title = "user create param")
public record UserCReq(
        @NotBlank(message = "username can't be blank")
        @Size(min = 1, max = 20, message = "username too long or too short")
        @Schema(required = true)
        String username,

        @NotBlank(message = "password can't be null")
        @Size(min = 1, max = 20, message = "password too long or too short")
        @Schema(required = true)
        String password,

        @Size(min = 1, max = 20, message = "nickName too long or too short")
        String nickName,

        @Email
        String email) {

}
