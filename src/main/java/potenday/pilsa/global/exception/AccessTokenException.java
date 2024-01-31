package potenday.pilsa.global.exception;

import lombok.Getter;

@Getter
public class AccessTokenException extends AuthException {
    public AccessTokenException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
