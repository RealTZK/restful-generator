package io.tzk.restful.generator.core.support.repository;

import io.tzk.restful.generator.core.api.domain.entity.ApiGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tianzhenkun
 * @since 2023/4/12
 */
@Repository
public interface ApiGroupRepository extends JpaRepository<ApiGroup, Long> {
}
