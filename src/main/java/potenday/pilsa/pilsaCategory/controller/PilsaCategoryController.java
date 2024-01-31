package potenday.pilsa.pilsaCategory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potenday.pilsa.pilsaCategory.service.PilsaCategoryService;

@Tag(name = "필사 카테고리 Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pilsa/category")
public class PilsaCategoryController {

    private final PilsaCategoryService pilsaCategoryService;

    @Operation(summary = "필사 카테고리 리스트 조회", description = "")
    @GetMapping
    public ResponseEntity<?> getPilsaCategoryList() {

        return ResponseEntity.ok(null);
    }

}
