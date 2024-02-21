package potenday.pilsa.pilsa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import potenday.pilsa.global.dto.request.RequestPageDto;
import potenday.pilsa.login.Auth;
import potenday.pilsa.pilsa.dto.request.RequestGetPilsa;
import potenday.pilsa.pilsa.dto.request.RequestPilsaInfoDto;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaDetailDto;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaIncludeDetailDto;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaListDto;
import potenday.pilsa.pilsa.service.PilsaService;

import java.net.URI;

@Tag(name = "필사 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pilsa")
public class PilsaController {

    private final PilsaService pilsaService;

    @Operation(summary = "메인 페이지 전체 필사 리스트 조회(페이징처리)", description = "등록일 기준 내림차순 나열")
    @GetMapping("list")
    public ResponseEntity<ResponsePilsaListDto> getPilsaList(
            @Valid RequestPageDto request,
            @Parameter(hidden = true) @Auth(required = false) Long memberId) {

        ResponsePilsaListDto pilsaMainListDto = pilsaService.getAllPilsalList(request, memberId);

        return ResponseEntity.ok(pilsaMainListDto);
    }


    @Operation(summary = "내가 쓴 필사 리스트 조회(페이징처리)", description = "등록일 기준 내림차순 나열")
    @GetMapping
    public ResponseEntity<ResponsePilsaListDto> getPilsaListOfMember(
            @Parameter(hidden = true) @Auth Long memberId,
            @Valid RequestPageDto request) {

        ResponsePilsaListDto pilsaMainListDto = pilsaService.getPilsalListOfMember(memberId, request);

        return ResponseEntity.ok(pilsaMainListDto);
    }

    @Operation(summary = "필사 상세정보 조회", description = "")
    @GetMapping("{pilsaId}")
    public ResponseEntity<ResponsePilsaIncludeDetailDto> getPilsaDetail(
            @Parameter(hidden = true) @Auth(required = false) Long memberId,
            @PathVariable("pilsaId") Long pilsaId,
            @Valid RequestGetPilsa requestGetPilsa) {

        return ResponseEntity.ok(pilsaService.getPilsaDetail(pilsaId, memberId, requestGetPilsa));
    }


    @Operation(summary = "필사 등록", description = "")
    @PostMapping
    public ResponseEntity<ResponsePilsaDetailDto> createPilsaInfo(
            @Parameter(hidden = true) @Auth Long memberId,
            @RequestBody @Valid RequestPilsaInfoDto request) {

        ResponsePilsaDetailDto pilsaDetailDto = pilsaService.createPilsa(memberId, request);

        return ResponseEntity.ok(pilsaDetailDto);
    }


    @Operation(summary = "필사 수정", description = "")
    @PutMapping("{pilsaId}")
    public ResponseEntity<?> updatePilsaInfo(
            @Parameter(hidden = true) @Auth Long memberId,
            @PathVariable("pilsaId") Long pilsaId,
            @RequestBody @Valid RequestPilsaInfoDto request) {
        ResponsePilsaDetailDto responsePilsaDetailDto = pilsaService.updatePilsa(memberId, request, pilsaId);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(responsePilsaDetailDto);
    }


    @Operation(summary = "필사 삭제", description = "")
    @DeleteMapping("{pilsaId}")
    public ResponseEntity<Void> deletePilsaInfo(
            @Parameter(hidden = true) @PathVariable Long pilsaId,
            @Auth Long memberId) {
        pilsaService.deletePilsa(pilsaId, memberId);

        return ResponseEntity.noContent().build();
    }
}