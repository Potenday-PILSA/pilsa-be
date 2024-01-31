package potenday.pilsa.member.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import potenday.pilsa.login.Auth;
import potenday.pilsa.login.domain.TokenProvider;
import potenday.pilsa.login.dto.response.AccessTokenResponse;
import potenday.pilsa.login.dto.response.TokenPair;
import potenday.pilsa.member.dto.request.MemberUpdateRequest;
import potenday.pilsa.member.dto.response.MemberInfoResponse;
import potenday.pilsa.member.service.MemberService;

@Tag(name = "회원 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @Operation(summary = "토큰으로 회원 정보 가져오는 API", description = "")
    @GetMapping("/member")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(
            @Parameter(hidden = true) @Auth Long memberId) {

        return ResponseEntity.ok(memberService.getMemberInfo(memberId));
    }

    @Operation(summary = "회원의 소개글을 변경하는 API", description = "")
    @PutMapping("/member")
    public ResponseEntity<MemberInfoResponse> modifyMemberInfo(
            @Parameter(hidden = true) @Auth Long memberId,
            @RequestBody @Valid MemberUpdateRequest request) {

        return ResponseEntity.ok(memberService.updateMember(memberId, request));
    }

    @Operation(summary = "회원 번호 1의 테스트 엑세스토큰을 생성합니다.", description = "")
    @PostMapping("/test/get-access-token")
    public ResponseEntity<AccessTokenResponse> getToken() {
        String accessToken = tokenProvider.createAccessToken(1L);

        return ResponseEntity.ok(
                AccessTokenResponse.builder()
                .accessToken(accessToken)
                .build());
    }

    @Operation(summary = "회원 번호 1의 테스트 엑세스토큰&리프레쉬 토큰을 생성합니다.", description = "")
    @PostMapping("/test/get-all-token")
    public ResponseEntity<TokenPair> getAllToken() {
        TokenPair tokenPair = tokenProvider.createTokenPair(1L);

        return ResponseEntity.ok(tokenPair);
    }


}
