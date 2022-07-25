package io.tzk.restful.generator.admin.support.repository;

import io.tzk.restful.generator.admin.api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByAccount(String account);
}
