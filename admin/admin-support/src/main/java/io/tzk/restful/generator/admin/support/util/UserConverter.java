package io.tzk.restful.generator.admin.support.util;

import io.tzk.restful.generator.admin.api.domain.dto.req.UserCReq;
import io.tzk.restful.generator.admin.api.domain.dto.res.UserRes;
import io.tzk.restful.generator.admin.api.domain.entity.Role;
import io.tzk.restful.generator.admin.api.domain.entity.SysUser;
import org.mapstruct.Mapper;
import org.springframework.security.core.GrantedAuthority;

@Mapper(componentModel = "spring")
public interface UserConverter {

    SysUser convert(UserCReq req);

    UserRes convert(SysUser entity);

    default String convert(Role role){
        return role.getRoleName();
    }

    default String convert(GrantedAuthority authority){
        return authority.getAuthority();
    }
}
