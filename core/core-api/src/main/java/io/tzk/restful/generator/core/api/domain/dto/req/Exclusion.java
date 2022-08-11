package io.tzk.restful.generator.core.api.domain.dto.req;

import javax.validation.constraints.NotBlank;

public record Exclusion(
        @NotBlank
        String groupId,
        @NotBlank
        String artifactId) {
}
