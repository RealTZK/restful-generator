package io.tzk.restful.generator.admin.support.util;

import io.tzk.restful.generator.admin.api.domain.dto.req.UserCReq;
import io.tzk.restful.generator.admin.api.domain.dto.res.UserRes;
import io.tzk.restful.generator.admin.api.domain.entity.Role;
import io.tzk.restful.generator.admin.api.domain.entity.User;
import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;

@Mapper(componentModel = "spring")
public interface UserConverter {

    User convert(UserCReq condition);

    UserRes convert(User entity);

    default String convert(Role role){
        return role.getRoleName();
    }

    default String convert(GrantedAuthority authority){
        return authority.getAuthority();
    }
}
