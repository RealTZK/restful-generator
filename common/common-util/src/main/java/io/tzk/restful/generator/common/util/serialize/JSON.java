package io.tzk.restful.generator.common.util.serialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.tzk.restful.generator.common.util.exception.JsonDeserializeException;
import io.tzk.restful.generator.common.util.exception.JsonSerializeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JSON {

    private final ObjectMapper objectMapper;

    public String serialize(Object content) {
        try {
            return objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            throw new JsonSerializeException(e.getMessage(), e);
        }
    }

    public <T> T deserialize(String content, Class<T> type) {
        try {
            return objectMapper.readValue(content, type);
        } catch (JsonProcessingException e) {
            throw new JsonDeserializeException(e.getMessage(), e);
        }
    }
}
