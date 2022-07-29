package io.tzk.restful.generator.admin.api.service;

import io.tzk.restful.generator.admin.api.domain.dto.AuthReq;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

    UserDetails login(AuthReq authReq);
}
