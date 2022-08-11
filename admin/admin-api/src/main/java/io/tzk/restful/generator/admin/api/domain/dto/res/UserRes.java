package io.tzk.restful.generator.admin.api.domain.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collection;

@Schema(title = "user search response")
public record UserRes(
        String username,
        String nickname,
        String email,
        Boolean accountNonExpired,
        Boolean accountNonLocked,
        Boolean credentialsNonExpired,
        Boolean enabled,
        Collection<String> roles,
        Collection<String> authorities) {
}
