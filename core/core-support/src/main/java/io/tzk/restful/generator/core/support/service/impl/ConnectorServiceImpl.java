package io.tzk.restful.generator.core.support.service.impl;

import io.tzk.restful.generator.core.api.domain.dto.req.ConnectorCReq;
import io.tzk.restful.generator.core.api.service.ConnectorService;
import lombok.RequiredArgsConstructor;
import org.eclipse.aether.RepositorySystemSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConnectorServiceImpl implements ConnectorService {

    private final RepositorySystemSession repositorySystemSession;

    @Override
    public Long save(ConnectorCReq condition) {
        return null;
    }
}
