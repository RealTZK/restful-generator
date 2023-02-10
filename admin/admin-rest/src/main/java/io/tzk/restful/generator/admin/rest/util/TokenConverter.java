package io.tzk.restful.generator.admin.rest.util;

import io.tzk.restful.generator.admin.api.domain.dto.TokenBody;
import io.tzk.restful.generator.admin.api.domain.entity.SysUser;
import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring")
public interface TokenConverter {

    TokenBody convert(UserDetails userDetails);

    SysUser convert(TokenBody tokenBody);

}
