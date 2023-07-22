package io.tzk.restful.generator.core.support.util;

import io.tzk.restful.generator.core.api.domain.dto.req.ApiGroupCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.ApiGroupRes;
import io.tzk.restful.generator.core.api.domain.entity.ApiGroup;
import org.mapstruct.Mapper;

/**
 * @author tianzhenkun
 * @since 2023/4/12
 */
@Mapper(componentModel = "spring")
public interface ApiGroupConverter {

    ApiGroupRes convert(ApiGroup entity);

    ApiGroup convert(ApiGroupCReq req);
}
