package io.tzk.restful.generator.core.support.util;

import io.tzk.restful.generator.core.api.domain.dto.req.DataApiCReq;
import io.tzk.restful.generator.core.api.domain.entity.DataApi;
import org.mapstruct.Mapper;

/**
 * @author tianzhenkun
 * @since 2023/4/12
 */
@Mapper(componentModel = "spring")
public interface DataApiConverter {

    DataApi convert(DataApiCReq req);
}
