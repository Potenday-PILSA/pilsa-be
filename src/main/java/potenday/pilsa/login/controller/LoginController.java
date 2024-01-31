package potenday.pilsa.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import potenday.pilsa.login.dto.request.LoginRequest;
import potenday.pilsa.login.dto.response.AccessTokenResponse;
import potenday.pilsa.login.dto.response.TokenPair;
import potenday.pilsa.login.service.LoginService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(
            @RequestBody LoginRequest request) {
        TokenPair tokenPair = loginService.login(request);

        // 엑세스 토큰은 프론트로 보내고 리프레쉬 토큰은 쿠키에 저장한다.
        AccessTokenResponse accessTokenRes = AccessTokenResponse.builder()
                .accessToken(tokenPair.getAccessToken())
                .build();

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", tokenPair.getRefreshToken())
                .maxAge(3600) // 쿠키 유효 시간 설정 (초 단위)
                .httpOnly(true) // HttpOnly 속성 설정 (JavaScript에서 접근 불가)
//                .secure(true) // HTTPS에서만 전송되도록 설정 (선택적) TODO : 개발환경에서는 X
                .path("/") // 쿠키의 경로 설정 (루트 경로로 설정)
                .build();

        // body 에는 엑세스토큰을 보내고 쿠키에는 리프레쉬 토큰 저장
        return ResponseEntity.ok()
                .header("Set-Cookie", refreshTokenCookie.toString())
                .body(accessTokenRes);
    }

    @PostMapping("/token")
    public ResponseEntity<?> extendLogin(
            @RequestHeader("Authorization") final String authorizationHeader,
            @CookieValue("refresh-token") final String refreshToken) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loginService.renewAccessToken(authorizationHeader, refreshToken));
    }
}
