package app.apiservice.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class BlankFileException extends RuntimeException {
    public BlankFileException(String message) {
        super(message);
    }

    public BlankFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
