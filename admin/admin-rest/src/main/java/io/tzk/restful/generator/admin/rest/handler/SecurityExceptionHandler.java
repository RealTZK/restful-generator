package io.tzk.restful.generator.admin.rest.handler;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class SecurityExceptionHandler {

    private final SQLExceptionDispatcher sqlExceptionDispatcher;

    /**
     * authorize error
     */
    @ExceptionHandler({JwtException.class, AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authorizeError(Exception e) {
        return e.getMessage();
    }

    /**
     * access error
     */
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessError(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> clientError(HttpClientErrorException e) {
        if (!e.getStatusCode().is4xxClientError()) {
            log.error("wrong client error http status: [%s]".formatted(e.getStatusCode()), e);
        }
        return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> serverError(HttpServerErrorException e) {
        if (!e.getStatusCode().is5xxServerError()) {
            log.error("wrong server error http status: [%s]".formatted(e.getStatusCode()), e);
        }
        return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> sqlErrorDispatch(SQLException e) {
        HttpStatus httpStatus = sqlExceptionDispatcher.dispatch(e);
        return new ResponseEntity<>(e.getLocalizedMessage(), httpStatus);
    }
}
