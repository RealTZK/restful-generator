package io.tzk.restful.generator.core.support.util;

import io.tzk.restful.generator.core.api.domain.dto.req.SourceCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.SourceRes;
import io.tzk.restful.generator.core.api.domain.entity.Source;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceConverter {

    Source convert(SourceCReq req);

    SourceRes convert(Source entity);
}
