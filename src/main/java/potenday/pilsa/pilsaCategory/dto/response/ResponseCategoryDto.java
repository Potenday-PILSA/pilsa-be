package potenday.pilsa.pilsaCategory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseCategoryDto {

        @Schema(description = "카테고리 코드")
        private Long categoryCd;
        @Schema(description = "카테고리 명")
        private String categoryName;
        @Schema(description = "카테고리 설명")
        private String description;

    @Builder
    public ResponseCategoryDto(Long categoryCd, String categoryName, String description) {
        this.categoryCd = categoryCd;
        this.categoryName = categoryName;
        this.description = description;
    }

    public static ResponseCategoryDto from(PilsaCategory pilsaCategory) {
        return ResponseCategoryDto.builder()
                .categoryCd(pilsaCategory.getCategoryCd())
                .categoryName(pilsaCategory.getCategoryName())
                .description(pilsaCategory.getDescription())
                .build();
    }
}
