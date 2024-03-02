package potenday.pilsa.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potenday.pilsa.challenge.dto.request.RequestCreateChallenge;
import potenday.pilsa.challenge.dto.response.ResponseChallengeInfo;
import potenday.pilsa.challenge.service.ChallengeService;
import potenday.pilsa.login.Auth;

@Tag(name = "챌린지")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Operation(summary = "챌린지 등록")
    @PostMapping
    public ResponseEntity<ResponseChallengeInfo> createChallenge(
            @Parameter(hidden = true) @Auth Long memberId,
            @RequestBody RequestCreateChallenge requestCreateChallenge) {

        return ResponseEntity.ok(challengeService.createChallenge(memberId, requestCreateChallenge));
    }

}
