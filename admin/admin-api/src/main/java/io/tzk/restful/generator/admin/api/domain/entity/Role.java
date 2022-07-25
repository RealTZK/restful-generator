package io.tzk.restful.generator.admin.api.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.tzk.restful.generator.common.api.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@Schema(title = "角色信息")
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Schema(title = "角色")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
