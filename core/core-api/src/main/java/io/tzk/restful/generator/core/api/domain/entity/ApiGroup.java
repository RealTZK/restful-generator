package io.tzk.restful.generator.core.api.domain.entity;

import io.tzk.restful.generator.common.api.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author tianzhenkun
 * @since 2023/3/30
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
public class ApiGroup extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String groupCode;

    @Column(nullable = false)
    private String groupName;

    private String description;
}
