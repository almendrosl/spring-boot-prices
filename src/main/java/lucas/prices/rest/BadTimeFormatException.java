package lucas.prices.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadTimeFormatException  extends RuntimeException {
    public BadTimeFormatException(String message) {
        super(message);
    }
}
