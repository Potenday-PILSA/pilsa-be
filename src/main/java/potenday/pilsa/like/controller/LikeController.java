package potenday.pilsa.like.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potenday.pilsa.global.dto.request.RequestPageDto;
import potenday.pilsa.like.dto.response.ResponseLikePilsaDetailDto;
import potenday.pilsa.like.dto.response.ResponseLikePilsaListDto;
import potenday.pilsa.like.service.LikeService;
import potenday.pilsa.login.Auth;

@Tag(name = "좋아요 Controller")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/like")
public class LikeController {

    private final LikeService likeService;

    @Operation(summary = "내가 좋아요한 필사리스트 조회(페이징처리)", description = "좋아요한 날짜 기준 내림차순 정렬")
    @GetMapping
    public ResponseEntity<ResponseLikePilsaListDto> getLikePilsaListOfMember(
            @Valid RequestPageDto request
            ) {

        return ResponseEntity.ok(null);
    }

}
