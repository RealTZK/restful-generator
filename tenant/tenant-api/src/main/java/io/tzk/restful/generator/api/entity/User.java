package io.tzk.restful.generator.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.tzk.restful.generator.common.api.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Data
@SuperBuilder
@NoArgsConstructor
@Schema(title = "用户信息")
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @Schema(title = "账户")
    @Column(unique = true)
    private String account;

    @Schema(title = "用户密码", accessMode = AccessMode.WRITE_ONLY)
    private String password;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "电话")
    private String phone;

    @Schema(title = "账户是否过期")
    private Boolean accountNonExpired;
    @Schema(title = "账户是否锁定")
    private Boolean accountNonLocked;
    @Schema(title = "密码是否过期")
    private Boolean credentialsNonExpired;
    @Schema(title = "账号是否可用")
    private Boolean enabled;

    @Schema(title = "角色")
    @ManyToMany
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
