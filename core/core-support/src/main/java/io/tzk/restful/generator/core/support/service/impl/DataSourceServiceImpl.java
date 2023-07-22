package io.tzk.restful.generator.core.support.service.impl;

import io.tzk.restful.generator.core.api.domain.dto.req.DataSourceCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.SourceRes;
import io.tzk.restful.generator.core.api.service.DataSourceService;
import io.tzk.restful.generator.core.support.repository.DataSourceRepository;
import io.tzk.restful.generator.core.support.util.DataSourceConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DataSourceServiceImpl implements DataSourceService {

    private final DataSourceRepository dataSourceRepository;

    private final DataSourceConverter dataSourceConverter;

    @Override
    public Long create(DataSourceCReq req) {
        return dataSourceRepository.save(dataSourceConverter.convert(req)).getId();
    }

    @Override
    public SourceRes get(Long id) {
        return dataSourceRepository.findById(id)
                .map(dataSourceConverter::convert)
                .orElseThrow(()-> new NoSuchElementException("The data source with id [%d] does not exist".formatted(id)));
    }
}
