package io.tzk.restful.generator.admin.rest.handler;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.jdbc.support.SQLErrorCodesFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class SQLExceptionDispatcher {

    private final DataSource dataSource;

    public HttpStatus dispatch(SQLException sqlException) {
        SQLErrorCodes errorCodes = SQLErrorCodesFactory.getInstance().getErrorCodes(dataSource);
        String errorCode = sqlException.getSQLState();
        if (ArrayUtils.contains(errorCodes.getDuplicateKeyCodes(), errorCode)) {
            return HttpStatus.CONFLICT;
        } else if (ArrayUtils.contains(errorCodes.getDataIntegrityViolationCodes(), errorCode)) {
            return HttpStatus.BAD_REQUEST;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
