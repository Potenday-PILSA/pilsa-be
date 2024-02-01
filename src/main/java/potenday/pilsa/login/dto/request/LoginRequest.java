package potenday.pilsa.login.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.member.domain.SocialType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "로그인 RequestDto")
public class LoginRequest {
    @Schema(description = "소셜 타입 [KAKAO : 카카오, GOOGLE : 구글, ETC : 그외]")
    private SocialType socialType;
    @Schema(description = "소셜 로그인 시 받아오는 인가코드")
    private String authCode;
    @Schema(description = "요청이 localhost:3030 일 경우 true / 그외 false")
    private Boolean isLocal;

    @Builder
    public LoginRequest(SocialType socialType, String authCode, Boolean isLocal) {
        this.socialType = socialType;
        this.authCode = authCode;
        this.isLocal = isLocal;
    }
}
