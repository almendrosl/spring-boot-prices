package lucas.prices.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class DateTimeFormatException extends RuntimeException {
    public DateTimeFormatException(String message) {
        super(message);
    }
}
