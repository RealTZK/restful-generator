package io.tzk.restful.generator.admin.api.domain.dto;

import io.tzk.restful.generator.admin.api.domain.PathGrantedAuthority;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public record TokenBody(
        @NotNull String username,
        @NotNull Boolean accountNonExpired,
        @NotNull Boolean accountNonLocked,
        @NotNull Boolean credentialsNonExpired,
        @NotNull Boolean enabled,
        Collection<PathGrantedAuthority> authorities) {

}
