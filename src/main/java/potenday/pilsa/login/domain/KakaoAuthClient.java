package potenday.pilsa.login.domain;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import potenday.pilsa.login.dto.request.KakaoOauthAccessTokenRequest;
import potenday.pilsa.login.dto.response.KakaoOauthAccessTokenResponse;

@FeignClient(name = "kakaoAuthClient", url = "${oauth2.kakao.token-base-uri}")
public interface KakaoAuthClient {
    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    ResponseEntity<KakaoOauthAccessTokenResponse> getAccessToken(@RequestBody KakaoOauthAccessTokenRequest request);

}
