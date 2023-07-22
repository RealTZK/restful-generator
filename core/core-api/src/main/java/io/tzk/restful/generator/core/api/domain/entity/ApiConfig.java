package io.tzk.restful.generator.core.api.domain.entity;

import io.tzk.restful.generator.common.api.entity.BaseEntity;
import io.tzk.restful.generator.core.api.enums.ApiConfigType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Map;

/**
 * @author tianzhenkun
 * @since 2023/4/19
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
public class ApiConfig extends BaseEntity {

    @Column(nullable = false)
    private ApiConfigType configType;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, Object> params;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean enable;

}
