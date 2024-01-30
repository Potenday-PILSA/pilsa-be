package potenday.pilsa.login.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import potenday.pilsa.login.dto.response.KakaoGetUserInfoResponse;

@FeignClient(name = "KakaoMemberInfoClient", url = "${oauth2.kakao.member-info-base-uri}")
public interface KakaoMemberInfoClient {
    @GetMapping(value = "/v2/user/me")
    ResponseEntity<KakaoGetUserInfoResponse> getMemberInfo(
            @RequestHeader("Authorization") String authorizationHeader);
}
