package io.tzk.restful.generator.admin.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.tzk.restful.generator.admin.api.domain.PathGrantedAuthority;
import io.tzk.restful.generator.common.api.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "users")
@DynamicInsert
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String nickname;

    private String email;

    @Column(columnDefinition = "boolean default true")
    private Boolean accountNonExpired;

    @Column(columnDefinition = "boolean default true")
    private Boolean accountNonLocked;

    @Column(columnDefinition = "boolean default true")
    private Boolean credentialsNonExpired;

    @Column(columnDefinition = "boolean default true")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roles;

    @Transient
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Objects.nonNull(authorities) ? authorities : Optional.ofNullable(roles)
                .stream()
                .flatMap(Collection::stream)
                .map(Role::getAuthorities)
                .flatMap(Collection::stream)
                .map(PathGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
