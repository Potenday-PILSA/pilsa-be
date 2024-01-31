package potenday.pilsa.member.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import potenday.pilsa.login.Auth;
import potenday.pilsa.login.domain.TokenProvider;

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

}
