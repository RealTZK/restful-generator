package io.tzk.restful.generator.core.api.domain.entity;

import io.tzk.restful.generator.common.api.entity.BaseEntity;
import io.tzk.restful.generator.core.api.enums.DataSourceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
public class DataSource extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DataSourceType type;

    @Column(nullable = false)
    private String url;

    private String username;

    private String password;
}
