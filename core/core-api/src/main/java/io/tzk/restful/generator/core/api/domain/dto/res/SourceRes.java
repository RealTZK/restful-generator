package io.tzk.restful.generator.core.api.domain.dto.res;

import io.tzk.restful.generator.core.api.enums.Connector;
import io.tzk.restful.generator.core.api.enums.SourceType;

public record SourceRes(
        String name,
        SourceType type,
        String url,
        String username,
        Connector connector) {
}
