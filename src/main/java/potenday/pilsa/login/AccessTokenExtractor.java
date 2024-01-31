package potenday.pilsa.login;

import org.springframework.stereotype.Component;

@Component
public class AccessTokenExtractor {
    private final String BEARER_TYPE = "Bearer";

    public String extractAccessToken(String authorizationHeader) {
        if(authorizationHeader==null || !authorizationHeader.startsWith(BEARER_TYPE)) {
            throw new NullPointerException();
        }

        return authorizationHeader.substring(BEARER_TYPE.length()).trim();
    }
}
