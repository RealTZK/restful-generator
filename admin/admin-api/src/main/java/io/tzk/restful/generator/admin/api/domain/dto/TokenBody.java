package io.tzk.restful.generator.admin.api.domain.dto;

import io.tzk.restful.generator.admin.api.domain.PathGrantedAuthority;

import java.util.Collection;

public record TokenBody(
        String username,
        Boolean accountNonExpired,
        Boolean accountNonLocked,
        Boolean credentialsNonExpired,
        Boolean enabled,
        Collection<PathGrantedAuthority> authorities) {

}
