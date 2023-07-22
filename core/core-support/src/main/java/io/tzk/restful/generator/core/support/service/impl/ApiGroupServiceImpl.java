package io.tzk.restful.generator.core.support.service.impl;

import io.tzk.restful.generator.core.api.domain.dto.req.ApiGroupCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.ApiGroupRes;
import io.tzk.restful.generator.core.api.domain.entity.ApiGroup;
import io.tzk.restful.generator.core.api.service.ApiGroupService;
import io.tzk.restful.generator.core.support.repository.ApiGroupRepository;
import io.tzk.restful.generator.core.support.util.ApiGroupConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * @author tianzhenkun
 * @since 2023/4/12
 */
@Service
@RequiredArgsConstructor
public class ApiGroupServiceImpl implements ApiGroupService {

    private final ApiGroupRepository apiGroupRepository;

    private final ApiGroupConverter apiGroupConverter;

    @Override
    public ApiGroupRes get(Long groupId) {
        return apiGroupRepository.findById(groupId)
                .map(apiGroupConverter::convert)
                .orElseThrow(()-> new NoSuchElementException("The api group with id [%d] does not exist".formatted(groupId)));
    }

    @Override
    public Long create(ApiGroupCReq req) {
        ApiGroup apiGroup = apiGroupConverter.convert(req);
        return apiGroupRepository.save(apiGroup).getId();
    }
}
