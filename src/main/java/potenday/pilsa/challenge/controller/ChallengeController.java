package potenday.pilsa.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import potenday.pilsa.challenge.dto.request.RequestChallengeList;
import potenday.pilsa.challenge.dto.request.RequestChallengePilsa;
import potenday.pilsa.challenge.dto.request.RequestCreateChallenge;
import potenday.pilsa.challenge.dto.request.RequestModifyChallenge;
import potenday.pilsa.challenge.dto.response.ResponseChallengeInfo;
import potenday.pilsa.challenge.dto.response.ResponseChallengeList;
import potenday.pilsa.challenge.dto.response.ResponseChallengePilsaInfo;
import potenday.pilsa.challenge.dto.response.ResponseChallengePilsaInfoList;
import potenday.pilsa.challenge.service.ChallengeService;
import potenday.pilsa.login.Auth;

import java.net.URI;

@Tag(name = "챌린지")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Operation(summary = "챌린지 등록")
    @PostMapping
    public ResponseEntity<Void> createChallenge(
            @Parameter(hidden = true) @Auth Long memberId,
            @RequestBody RequestCreateChallenge requestCreateChallenge) {

        Long challengeId = challengeService.createChallenge(memberId, requestCreateChallenge);

        return ResponseEntity.created(URI.create("challenge/" + challengeId)).build();
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
            @Valid RequestChallengeList requestPageDto) {

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

    @Operation(summary = "진행중인 챌린지의 상태를 성공과 실패로 나누어서 변화시키는 API")
    @PostMapping("/change-statue")
    public ResponseEntity<Void> changeStatue(
            @Parameter(hidden = true) @Auth Long memberId) {

        challengeService.changeINGStatueSuccessOrFail(memberId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "챌린지 수정 API")
    @PutMapping("/{challengeId}")
    public ResponseEntity<ResponseChallengeInfo> modifyChallenge(
            @Parameter(hidden = true) @Auth Long memberId,
            @PathVariable("challengeId") Long challengeId,
            @RequestBody @Valid RequestModifyChallenge request) {

        challengeService.modifyChallenge(memberId, challengeId, request);

        return ResponseEntity.created(URI.create("challenge/" + challengeId)).build();
    }

    @Operation(summary = "챌린지에 해당하는 필사 리스트 조회")
    @GetMapping("/pilsa")
    public ResponseEntity<ResponseChallengePilsaInfoList> getChallengePilsas(
            @Parameter(hidden = true) @Auth Long memberId,
            @Valid RequestChallengePilsa requestChallengePilsa) {

        return ResponseEntity.ok(challengeService.getChallengePilsa(memberId, requestChallengePilsa));
    }

}
