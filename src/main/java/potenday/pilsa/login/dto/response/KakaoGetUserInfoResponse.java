package potenday.pilsa.login.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoGetUserInfoResponse {
    private Long id;
    private KakaoAccount kakao_account;

    @Getter
    @Setter
    @ToString
    public static class KakaoAccount {
        private Profile profile;
        private String email;
    }

    @Getter
    @Setter
    @ToString
    public static class Profile {
        private String nickname;
        private String profile_image_url;
    }
}
