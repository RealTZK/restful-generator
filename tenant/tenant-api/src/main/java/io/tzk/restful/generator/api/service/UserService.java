package io.tzk.restful.generator.api.service;

import io.tzk.restful.generator.api.entity.User;

public interface UserService {
    User getById(Long id);

    Long save(User condition);
}
