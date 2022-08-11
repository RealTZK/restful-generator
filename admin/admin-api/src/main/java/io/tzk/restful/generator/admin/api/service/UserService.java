package io.tzk.restful.generator.admin.api.service;

import io.tzk.restful.generator.admin.api.domain.dto.req.UserCReq;
import io.tzk.restful.generator.admin.api.domain.dto.res.UserRes;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRes getById(Long id);

    Long save(UserCReq condition);
}
