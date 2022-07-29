package io.tzk.restful.generator.admin.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Schema(title = "用户信息")
@Entity
@Table(name = "users")
@DynamicInsert
public class User extends BaseEntity implements UserDetails {

    @Schema(title = "账户")
    @Column(unique = true, nullable = false)
    private String username;

    @Schema(title = "用户密码", accessMode = AccessMode.WRITE_ONLY)
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Schema(title = "用户名")
    private String nickname;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "电话")
    private String phone;

    @Schema(title = "账户未过期", accessMode = AccessMode.WRITE_ONLY)
    @Column(columnDefinition = "boolean default true")
    private Boolean accountNonExpired;

    @Schema(title = "账户未锁定", accessMode = AccessMode.WRITE_ONLY)
    @Column(columnDefinition = "boolean default true")
    private Boolean accountNonLocked;

    @Schema(title = "密码未过期", accessMode = AccessMode.WRITE_ONLY)
    @Column(columnDefinition = "boolean default true")
    private Boolean credentialsNonExpired;

    @Schema(title = "账号可用", accessMode = AccessMode.WRITE_ONLY)
    @Column(columnDefinition = "boolean default true")
    private Boolean enabled;

    @Schema(hidden = true)
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Transient
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
