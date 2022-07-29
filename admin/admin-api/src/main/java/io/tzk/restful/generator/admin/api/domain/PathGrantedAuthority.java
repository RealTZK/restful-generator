package io.tzk.restful.generator.admin.api.domain;

import org.springframework.security.core.GrantedAuthority;

public record PathGrantedAuthority(String authority) implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return authority;
    }
}
