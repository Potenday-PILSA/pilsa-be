package potenday.pilsa.global.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import potenday.pilsa.global.dto.response.CustomErrorResponse;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        String errorMessage = ex.getBindingResult().getAllErrors().isEmpty() ? "Validation failed. Please check the request."
                : ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
