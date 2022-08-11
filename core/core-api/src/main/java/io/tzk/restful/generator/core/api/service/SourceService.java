package io.tzk.restful.generator.core.api.service;

import io.tzk.restful.generator.core.api.domain.dto.req.SourceCReq;

public interface SourceService {

    Long save(SourceCReq condition);
}
