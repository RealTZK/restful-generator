package io.tzk.restful.generator.core.api.service;

import io.tzk.restful.generator.core.api.domain.dto.req.ApiConfigCReq;
import io.tzk.restful.generator.core.api.domain.dto.req.DataApiCReq;

/**
 * @author tianzhenkun
 * @since 2023/3/29
 */
public interface DataApiService {

    Long create(Long groupId, DataApiCReq req);

    Long addConfig(Long groupId, Long apiId, ApiConfigCReq req);
}
