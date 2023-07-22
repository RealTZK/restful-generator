package io.tzk.restful.generator.core.api.domain.dto.req;

import io.tzk.restful.generator.core.api.enums.ApiConfigType;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

/**
 * @author tianzhenkun
 * @since 2023/4/20
 */
public record ApiConfigCReq(
        @NotNull(message = "config type can't be null")
        ApiConfigType configType,
        Map<String, Object> params) {

}
