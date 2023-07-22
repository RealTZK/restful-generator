package io.tzk.restful.generator.admin.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.tzk.restful.generator.common.api.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
public class Role extends BaseEntity {

    public static final String GUEST = "guest";
    public static final String ADMIN = "admin";

    @Column(nullable = false, unique = true)
    private String roleName;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorities;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<SysUser> sysUsers;
}
