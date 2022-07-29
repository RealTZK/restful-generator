package io.tzk.restful.generator.common.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Schema(title = "id", accessMode = AccessMode.READ_ONLY)
    @Id
    @GeneratedValue
    private Long id;

    @Schema(title = "创建用户", accessMode = AccessMode.READ_ONLY)
    @CreatedBy
    @Column(nullable = false)
    private String createUser;

    @Schema(title = "更新用户", accessMode = AccessMode.READ_ONLY)
    @LastModifiedBy
    @Column(nullable = false)
    private String updateUser;

    @Schema(title = "创建时间", accessMode = AccessMode.READ_ONLY)
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createTime;

    @Schema(title = "更新时间", accessMode = AccessMode.READ_ONLY)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateTime;

    @Schema(title = "版本号", accessMode = AccessMode.READ_ONLY)
    @Version
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
