package potenday.pilsa.pilsaCategory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import potenday.pilsa.pilsaCategory.dto.request.RequestCategoryDto;
import potenday.pilsa.pilsaCategory.dto.response.ResponseCategoryListDto;
import potenday.pilsa.pilsaCategory.service.PilsaCategoryService;

@Tag(name = "필사 카테고리 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pilsa/category")
public class PilsaCategoryController {

    private final PilsaCategoryService pilsaCategoryService;

    @Operation(summary = "필사 카테고리 리스트 조회", description = "")
    @GetMapping
    public ResponseEntity<ResponseCategoryListDto> getPilsaCategoryList() {

        ResponseCategoryListDto categoryListDto = pilsaCategoryService.getPilsaCategoryList();

        return ResponseEntity.ok(categoryListDto);
    }

    @Operation(summary = "필사 카테고리 저장", description = "")
    @PostMapping
    public ResponseEntity<Void> createPilsaCategory(
            @RequestBody RequestCategoryDto requestCategoryDto) {
        pilsaCategoryService.createPilsaCategory(requestCategoryDto);

        return ResponseEntity.ok().build();
    }

}
