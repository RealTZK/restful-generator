package io.tzk.restful.generator.core.api.service;

import io.tzk.restful.generator.core.api.domain.dto.req.DataSourceCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.SourceRes;

public interface DataSourceService {

    Long create(DataSourceCReq req);

    SourceRes get(Long id);
}
