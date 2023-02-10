package io.tzk.restful.generator.core.support.service.impl;

import io.tzk.restful.generator.core.api.domain.dto.req.SourceCReq;
import io.tzk.restful.generator.core.api.domain.dto.res.SourceRes;
import io.tzk.restful.generator.core.api.service.SourceService;
import io.tzk.restful.generator.core.support.repository.SourceRepository;
import io.tzk.restful.generator.core.support.util.SourceConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;

    private final SourceConverter sourceConverter;

    @Override
    public Long save(SourceCReq req) {
        return sourceRepository.save(sourceConverter.convert(req)).getId();
    }

    @Override
    public SourceRes get(Long id) {
        return sourceRepository.findById(id)
                .map(sourceConverter::convert)
                .orElseThrow(()-> new NoSuchElementException("The source with id [%d] does not exist".formatted(id)));
    }
}
