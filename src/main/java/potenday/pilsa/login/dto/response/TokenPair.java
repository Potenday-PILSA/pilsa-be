package potenday.pilsa.login.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class TokenPair {
    private String accessToken;
    private String refreshToken;
    private Long refreshTokenExpirationTime;

    @Builder
    public TokenPair(String accessToken, String refreshToken, Long refreshTokenExpirationTime) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }
}
