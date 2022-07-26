package io.tzk.restful.generator.admin.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity
public class Role extends BaseEntity {

    public static final String READ_ONLY = "read-only";

    @Column(nullable = false, unique = true)
    private String roleName;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorities;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;
}
