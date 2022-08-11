package io.tzk.restful.generator.core.api.domain.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Schema(title = "connector create param")
public record ConnectorCReq(
        @NotBlank
        String name,

        @NotBlank
        String groupId,

        @NotBlank
        String artifactId,

        @NotBlank
        String version,

        @Min(value = 1L)
        Set<Exclusion> exclusions) {
}
