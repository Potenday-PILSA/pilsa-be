package potenday.pilsa.login.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import potenday.pilsa.login.dto.request.KakaoOauthAccessTokenRequest;
import potenday.pilsa.login.dto.request.LoginRequest;
import potenday.pilsa.login.dto.response.KakaoGetUserInfoResponse;
import potenday.pilsa.login.dto.response.KakaoOauthAccessTokenResponse;
import potenday.pilsa.login.dto.response.MemberInfoResponse;
import potenday.pilsa.member.domain.SocialType;

@Component
@RequiredArgsConstructor
public class KakaoProvider {
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoMemberInfoClient kakaoMemberInfoClient;

    @Value("${oauth2.kakao.client-id}")
    private String CLIENT_ID;

    @Value("${oauth2.kakao.redirect-uri}")
    private String REDIRECT_URI;

    @Value("${oauth2.kakao.redirect-uri-local}")
    private String REDIRECT_URI_LOCAL;

    @Value("${oauth2.kakao.grant-type}")
    private String GRANT_TYPE;

    @Value("${oauth2.kakao.client-secret}")
    private String CLIENT_SECRET;

    public String getAccessToken(LoginRequest loginRequest) {
        KakaoOauthAccessTokenRequest kakaoOauthAccessTokenRequest =
                KakaoOauthAccessTokenRequest.builder()
                        .client_id(CLIENT_ID)
                        .grant_type(GRANT_TYPE)
                        .client_secret(CLIENT_SECRET)
                        .redirect_uri(loginRequest.getRedirectUri())
                        .code(loginRequest.getAuthCode())
                .build();

        ResponseEntity<KakaoOauthAccessTokenResponse> response = kakaoAuthClient.getAccessToken(kakaoOauthAccessTokenRequest);

        // TODO : 에러 핸들링 하기
        if (!response.getStatusCode().is2xxSuccessful() || !response.hasBody()) {
            throw new NullPointerException();
        }

        return response.getBody().getAccess_token();
    }

    public MemberInfoResponse getMemberInfo(String accessToken) {
        ResponseEntity<KakaoGetUserInfoResponse> response = kakaoMemberInfoClient.getMemberInfo("Bearer " + accessToken);

        // TODO : 에러 핸들링 하기
        if (!response.getStatusCode().is2xxSuccessful() || !response.hasBody()) {
            throw new NullPointerException();
        }

        KakaoGetUserInfoResponse memberInfo = response.getBody();

        return MemberInfoResponse.builder()
                .socialType(SocialType.KAKAO)
                .memberKey(memberInfo.getId().toString())
                .nickName(memberInfo.getKakao_account().getProfile().getNickname())
                .imageUrl(memberInfo.getKakao_account().getProfile().getProfile_image_url())
                .email(memberInfo.getKakao_account().getEmail())
                .build();
    }
}
