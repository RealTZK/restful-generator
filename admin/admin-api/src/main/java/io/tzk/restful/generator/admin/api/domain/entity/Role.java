package io.tzk.restful.generator.admin.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import io.tzk.restful.generator.common.api.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Schema(title = "角色信息")
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Schema(title = "角色")
    @Column(nullable = false)
    private String roleName;

    @Schema(title = "权限")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorities;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    @Override
    public String toString() {
        return roleName;
    }
}
