package potenday.pilsa.pilsaBasicContents.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potenday.pilsa.global.dto.request.RequestPageDto;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaMainListDto;
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
}
