package potenday.pilsa.login;

import org.springframework.stereotype.Component;
import potenday.pilsa.global.exception.AuthException;
import potenday.pilsa.global.exception.ExceptionCode;

@Component
public class AccessTokenExtractor {
    private final String BEARER_TYPE = "Bearer";

    public String extractAccessToken(String authorizationHeader) {
        if(authorizationHeader==null || !authorizationHeader.startsWith(BEARER_TYPE)) {
            throw new AuthException(ExceptionCode.FAIL_TO_VALIDATE_TOKEN);
        }

        return authorizationHeader.substring(BEARER_TYPE.length()).trim();
    }
}
