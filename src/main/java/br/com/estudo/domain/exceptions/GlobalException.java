package br.com.estudo.domain.exceptions;

import io.micronaut.http.HttpStatus;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class GlobalException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public GlobalException() {
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    public GlobalException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
