package io.tzk.restful.generator.core.support.util;

import io.tzk.restful.generator.core.api.domain.dto.req.DataSourceCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.SourceRes;
import io.tzk.restful.generator.core.api.domain.entity.DataSource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataSourceConverter {

    DataSource convert(DataSourceCReq req);

    SourceRes convert(DataSource entity);
}
