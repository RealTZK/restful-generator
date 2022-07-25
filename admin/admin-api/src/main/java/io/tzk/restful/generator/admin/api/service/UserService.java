package io.tzk.restful.generator.admin.api.service;

import io.tzk.restful.generator.admin.api.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getById(Long id);

    Long save(User condition);
}
