package potenday.pilsa.pilsaBasicContents.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import potenday.pilsa.global.dto.request.RequestPageDto;
import potenday.pilsa.pilsaBasicContents.dto.response.ResponseBasicPilsaDto;
import potenday.pilsa.pilsaBasicContents.dto.response.ResponseBasicPilsaListDto;
import potenday.pilsa.pilsaBasicContents.service.PilsaBasicContentService;

@Tag(name = "램덤 기본제공 필사 콘텐츠 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pilsa/basic/contents")
public class PilsaBasicContentsController {

    private final PilsaBasicContentService pilsaBasicContentService;

    @Operation(summary = "핸덤으로 기본제공하는 필사 리스트 조회(페이징처리)")
    @GetMapping("list")
    public ResponseEntity<ResponseBasicPilsaListDto> getPilsaList(@Valid RequestPageDto request) {

        ResponseBasicPilsaListDto pilsaMainListDto = pilsaBasicContentService.getAllPilsalList(request);

        return ResponseEntity.ok(pilsaMainListDto);
    }

    @Operation(summary = "랜덤한 기본제공 필사 정보 조회")
    @GetMapping("category")
    public ResponseEntity<ResponseBasicPilsaDto> getBasicPilsaRandom() {

        ResponseBasicPilsaDto responseBasicPilsaDto = pilsaBasicContentService.getPilsaInfoRandom();

        return ResponseEntity.ok(responseBasicPilsaDto);
    }

    @Operation(summary = "카테고리별 랜덤한 기본제공 필사 정보 조회")
    @GetMapping("category/{categoryCd}")
    public ResponseEntity<ResponseBasicPilsaDto> getBasicPilsaRandomOfCategoryCd(@PathVariable Long categoryCd) {

        ResponseBasicPilsaDto responseBasicPilsaDto = pilsaBasicContentService.getPilsaInfoOfCategoryCd(categoryCd);

        return ResponseEntity.ok(responseBasicPilsaDto);
    }
}