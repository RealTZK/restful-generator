package io.tzk.restful.generator.core.api.service;

import io.tzk.restful.generator.core.api.domain.dto.req.ApiGroupCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.ApiGroupRes;

/**
 * @author tianzhenkun
 * @since 2023/4/12
 */
public interface ApiGroupService {

    ApiGroupRes get(Long groupId);

    Long create(ApiGroupCReq req);
}
