package io.tzk.restful.generator.core.api.domain.dto.res;

import io.tzk.restful.generator.core.api.enums.DataSourceType;

public record SourceRes(
        String name,
        DataSourceType type,
        String url,
        String username) {
}
