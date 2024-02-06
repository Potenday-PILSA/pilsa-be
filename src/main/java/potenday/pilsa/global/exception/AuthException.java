package potenday.pilsa.global.exception;

import lombok.Getter;

@Getter
public class AuthException extends BadRequestException {
    public AuthException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
