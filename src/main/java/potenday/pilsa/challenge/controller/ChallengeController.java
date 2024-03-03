package potenday.pilsa.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import potenday.pilsa.challenge.dto.request.RequestCreateChallenge;
import potenday.pilsa.challenge.dto.response.ResponseChallengeInfo;
import potenday.pilsa.challenge.dto.response.ResponseChallengeList;
import potenday.pilsa.challenge.service.ChallengeService;
import potenday.pilsa.global.dto.request.RequestPageDto;
import potenday.pilsa.login.Auth;

import java.util.List;

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

    @Operation(summary = "챌린지 상세 조회")
    @GetMapping("/{challengeId}")
    public ResponseEntity<ResponseChallengeInfo> getChallengeInfo(
            @PathVariable("challengeId") Long challengeId,
            @Parameter(hidden = true) @Auth Long memberId) {

        return ResponseEntity.ok(challengeService.getChallengeInfo(memberId, challengeId));
    }

    @Operation(summary = "챌린지 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<ResponseChallengeList> getChallengeList(
            @Parameter(hidden = true) @Auth Long memberId,
            @Valid RequestPageDto requestPageDto) {

        return ResponseEntity.ok(challengeService.getChallengeList(memberId, requestPageDto));
    }

    @Operation(summary = "챌린지 삭제")
    @DeleteMapping("/{challengeId}")
    public ResponseEntity<Void> deleteChallenge(
            @Parameter(hidden = true) @Auth Long memberId,
            @PathVariable("challengeId") Long challengeId) {

        challengeService.deleteChallenge(memberId, challengeId);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "챌린지 상태 변화")
    @PostMapping("/change-statue")
    public ResponseEntity<List<ResponseChallengeInfo>> changeStatue(
            @Parameter(hidden = true) @Auth Long memberId) {

        return ResponseEntity.ok(challengeService.changeStatue(memberId));
    }
}
