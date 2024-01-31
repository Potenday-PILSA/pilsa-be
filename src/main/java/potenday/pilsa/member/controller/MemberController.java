package potenday.pilsa.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import potenday.pilsa.login.Auth;
import potenday.pilsa.login.domain.TokenProvider;
import potenday.pilsa.login.dto.response.TokenPair;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final TokenProvider tokenProvider;

    @GetMapping("/member")
    public ResponseEntity<?> getMemberInfo(
            @Auth Long memberId,
            @RequestParam(value = "mode", required = false) String mode) {

        System.out.println("memberId1 = " + mode);
        System.out.println("memberId2 = " + memberId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public ResponseEntity<?> createToken() {
        TokenPair tokenPair = tokenProvider.createTokenPair(1L);

        System.out.println("tokenPair.getAccessToken() = " + tokenPair.getAccessToken());
        System.out.println("tokenPair = " + tokenPair.getRefreshToken());

        return ResponseEntity.ok().build();
    }

}
