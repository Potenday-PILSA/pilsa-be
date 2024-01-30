package potenday.pilsa.login.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.member.domain.SocialType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {
    private SocialType socialType;
    private String authCode;
    private Boolean isLocal;
}
