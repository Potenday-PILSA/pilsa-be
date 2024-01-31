package potenday.pilsa.global.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import potenday.pilsa.global.exception.BadRequestException;

@ControllerAdvice
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomResponseExceptionHandler.class);

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorResponse> handleAuthException(BadRequestException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getMessage());

        logger.error("ERROR: {}", ex.getMessage(), ex);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private record ErrorResponse(int code, String message) {
    }
}