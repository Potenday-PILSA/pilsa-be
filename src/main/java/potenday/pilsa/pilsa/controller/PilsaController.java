package potenday.pilsa.pilsa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.dto.request.RequestPilsaInfo;
import potenday.pilsa.pilsa.service.PilsaService;

import java.net.URI;

@Tag(name = "필사 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pilsa")
public class PilsaController {

    private final PilsaService pilsaService;

    @Operation(summary = "메인 페이지 전체 필사 리스트 조회(페이징처리)", description = "")
    @GetMapping("list")
    public ResponseEntity<?> getPilsaList() {

        return ResponseEntity.ok(null);
    }


    @Operation(summary = "내가 쓴 필사 리스트 조회(페이징처리)", description = "")
    @GetMapping
    public ResponseEntity<?> getPilsaListOfMember() {

        // TODO: 회원 정보 받아오기

        return ResponseEntity.ok(null);
    }

    @Operation(summary = "필사 상세정보 조회", description = "")
    @GetMapping("{pilsaId}")
    public ResponseEntity<?> getPilsaDetail(@PathVariable Long pilsaId) {

        return ResponseEntity.ok(null);
    }


    @Operation(summary = "필사 등록", description = "")
    @PostMapping
    public ResponseEntity<?> createPilsaInfo(@RequestBody @Valid RequestPilsaInfo request) {

        // TODO: 회원 정보 받아오기
        Pilsa pilsa = pilsaService.createPilsa(request);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).build();
    }


    @Operation(summary = "필사 수정", description = "")
    @PutMapping("{pilsaId}")
    public ResponseEntity<?> updatePilsaInfo(@PathVariable Long pilsaId) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).build();
    }


    @Operation(summary = "필사 리스트 삭제", description = "")
    @DeleteMapping("{pilsaId}")
    public ResponseEntity<?> deletePilsaInfo(@PathVariable Long pilsaId) {

        return ResponseEntity.noContent().build();
    }
}