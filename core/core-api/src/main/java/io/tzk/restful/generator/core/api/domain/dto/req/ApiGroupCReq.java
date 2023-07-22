package io.tzk.restful.generator.core.api.domain.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author tianzhenkun
 * @since 2023/4/12
 */
public record ApiGroupCReq(
        @NotBlank(message = "group code can't be blank")
        @Size(min = 1, max = 20, message = "group code too long or too short")
        String groupCode,
        @NotBlank(message = "group name can't be blank")
        @Size(min = 1, max = 20, message = "group name too long or too short")
        String groupName,
        String groupDesc) {
}
