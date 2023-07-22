package io.tzk.restful.generator.core.api.domain.entity;

import io.tzk.restful.generator.common.api.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Collection;

/**
 * @author tianzhenkun
 * @since 2023/3/29
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
public class DataApi extends BaseEntity {

    @Column(nullable = false)
    private String apiCode;

    @Column(nullable = false)
    private String apiName;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private ApiGroup apiGroup;

    private String description;

    private String apiVersion;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private DataSource dataSource;

    private String sentence;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean published;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<ApiConfig> apiConfigs;

}
