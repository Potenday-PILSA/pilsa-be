package potenday.pilsa.login.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoOauthAccessTokenResponse {
    @JsonProperty("token_type")
    private String token_type;
    @JsonProperty("access_token")
    private String access_token;
    @JsonProperty("id_token")
    private String id_token;
    @JsonProperty("expires_in")
    private Integer expires_in;
    @JsonProperty("refresh_token")
    private String refresh_token;
    @JsonProperty("refresh_token_expires_in")
    private Integer refresh_token_expires_in;
    @JsonProperty("scope")
    private String scope;
}
