package io.tzk.restful.generator.core.support.repository;

import io.tzk.restful.generator.core.api.domain.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {
}
