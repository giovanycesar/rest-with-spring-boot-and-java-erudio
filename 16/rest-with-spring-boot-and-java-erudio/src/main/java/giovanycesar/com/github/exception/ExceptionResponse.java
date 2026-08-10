package giovanycesar.com.github.exception;

import java.util.Date;

// ExceptionResponse: A standard object used to structure the error response sent to the client,
// usually containing information such as the timestamp, error message, and request details.

public record ExceptionResponse(Date timestamp, String message, String details) {
}
