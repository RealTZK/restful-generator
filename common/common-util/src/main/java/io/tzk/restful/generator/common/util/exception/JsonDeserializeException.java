package io.tzk.restful.generator.common.util.exception;

public class JsonDeserializeException extends RuntimeException {
    public JsonDeserializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
