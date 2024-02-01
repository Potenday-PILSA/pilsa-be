package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;
import potenday.pilsa.relationPilsaCategory.domain.RelationPilsaCategory;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ResponsePilsaMainCategoryDto {

    @Schema(description = "카테고리 코드")
    private Long categoryCd;
    @Schema(description = "카테고리 명")
    private String categoryName;
    @Schema(description = "카테고리 설명")
    private String categoryDescription;

    @Builder
    public ResponsePilsaMainCategoryDto(Long categoryCd, String categoryName, String categoryDescription) {
        this.categoryCd = categoryCd;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public static ResponsePilsaMainCategoryDto from(RelationPilsaCategory pilsaCategory) {
        return ResponsePilsaMainCategoryDto.builder()
                .categoryCd(pilsaCategory.getCategory().getCategoryCd())
                .categoryName(pilsaCategory.getCategory().getCategoryName())
                .categoryDescription(pilsaCategory.getCategory().getDescription())
                .build();
    }

}
