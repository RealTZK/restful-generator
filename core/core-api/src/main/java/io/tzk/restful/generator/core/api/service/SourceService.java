package io.tzk.restful.generator.core.api.service;

import io.tzk.restful.generator.core.api.domain.dto.req.SourceCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.SourceRes;

public interface SourceService {

    Long save(SourceCReq req);

    SourceRes get(Long id);
}
