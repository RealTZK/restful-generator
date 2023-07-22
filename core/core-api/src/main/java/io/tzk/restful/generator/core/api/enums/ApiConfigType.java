package io.tzk.restful.generator.core.api.enums;

import java.util.Map;

/**
 * @author tianzhenkun
 * @since 2023/4/19
 */
public enum ApiConfigType {
    CACHE {
        @Override
        public void validateConfig(Map<String, Object> params) {
            // todo
        }
    },
    AUTHENTICATION {
        @Override
        public void validateConfig(Map<String, Object> params) {
            // todo
        }
    },
    LIMIT {
        @Override
        public void validateConfig(Map<String, Object> params) {
            // todo
        }
    },
    ;

    public void validateConfig(Map<String, Object> params) {
        throw new AbstractMethodError();
    }
}
