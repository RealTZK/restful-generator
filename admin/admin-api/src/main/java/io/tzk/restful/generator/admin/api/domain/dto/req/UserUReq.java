package io.tzk.restful.generator.admin.api.domain.dto.req;


import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Schema(title = "user update param")
public record UserUReq(
        @Size(min = 1, max = 20, message = "username too long or too short")
        String username,

        @Size(min = 1, max = 20, message = "nickName too long or too short")
        String nickName,

        @Email
        String email) {

}
