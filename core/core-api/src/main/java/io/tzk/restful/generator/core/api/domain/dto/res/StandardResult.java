package io.tzk.restful.generator.core.api.domain.dto.res;

import lombok.Builder;

import java.util.Collection;
import java.util.Map;

/**
 * @author tianzhenkun
 * @since 2023/3/30
 */
@Builder
public record StandardResult(
        Integer status,
        String error,
        Collection<Map<String, Object>> result,
        Long total,
        String sentence) {

}
