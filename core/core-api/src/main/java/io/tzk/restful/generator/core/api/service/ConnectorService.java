package io.tzk.restful.generator.core.api.service;

import io.tzk.restful.generator.core.api.domain.dto.req.ConnectorCReq;

public interface ConnectorService {

    Long save(ConnectorCReq condition);
}
