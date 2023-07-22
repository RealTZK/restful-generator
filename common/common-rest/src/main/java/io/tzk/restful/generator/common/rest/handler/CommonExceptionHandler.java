package io.tzk.restful.generator.common.rest.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CommonExceptionHandler {

    private final SQLExceptionDispatcher sqlExceptionDispatcher;

    /**
     * not found
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(NoSuchElementException e){
        return e.getMessage();
    }

    /**
     * parameter error
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validError(MethodArgumentNotValidException e) {
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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalError(Exception e) {
        return e.getMessage();
    }
}
