package io.tzk.restful.generator.core.support.service.impl;

import io.tzk.restful.generator.core.api.domain.dto.req.ApiConfigCReq;
import io.tzk.restful.generator.core.api.domain.dto.req.DataApiCReq;
import io.tzk.restful.generator.core.api.domain.entity.ApiGroup;
import io.tzk.restful.generator.core.api.domain.entity.DataApi;
import io.tzk.restful.generator.core.api.domain.entity.DataSource;
import io.tzk.restful.generator.core.api.service.DataApiService;
import io.tzk.restful.generator.core.support.repository.ApiGroupRepository;
import io.tzk.restful.generator.core.support.repository.DataApiRepository;
import io.tzk.restful.generator.core.support.repository.DataSourceRepository;
import io.tzk.restful.generator.core.support.util.DataApiConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author tianzhenkun
 * @since 2023/4/12
 */
@Service
@RequiredArgsConstructor
public class DataApiServiceImpl implements DataApiService {

    private final DataApiRepository dataApiRepository;
    private final ApiGroupRepository apiGroupRepository;
    private final DataSourceRepository dataSourceRepository;
    private final DataApiConverter dataApiConverter;

    @Override
    public Long create(Long groupId, DataApiCReq req) {
        DataApi dataApi = dataApiConverter.convert(req);
        ApiGroup apiGroup = apiGroupRepository.findById(groupId)
                .orElseThrow(() -> new NoSuchElementException("The api group with id [%d] does not exist".formatted(groupId)));
        dataApi.setApiGroup(apiGroup);
        DataSource dataSource = dataSourceRepository.findById(req.sourceId())
                .orElseThrow(() -> new NoSuchElementException("The data source with id [%d] does not exist".formatted(req.sourceId())));
        dataApi.setDataSource(dataSource);
        return dataApiRepository.save(dataApi).getId();
    }

    @Override
    public Long addConfig(Long groupId, Long apiId, ApiConfigCReq req) {
        if (!apiGroupRepository.existsById(groupId)) {
            throw new NoSuchElementException("The api group with id [%d] does not exist".formatted(groupId));
        }
        DataApi dataApi = dataApiRepository.findById(groupId)
                .orElseThrow(() -> new NoSuchElementException("The data api with id [%d] does not exist".formatted(apiId)));
        if (!Objects.equals(groupId, dataApi.getApiGroup().getId())) {
            throw new IllegalArgumentException("The data api is not belong to the group id");
        }
        return null;
    }
}
