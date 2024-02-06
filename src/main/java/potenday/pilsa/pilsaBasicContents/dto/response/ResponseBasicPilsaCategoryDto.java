package potenday.pilsa.pilsaBasicContents.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;
import potenday.pilsa.relationPilsaCategory.domain.RelationPilsaCategory;

@Getter
public class ResponseBasicPilsaCategoryDto {

    @Schema(description = "카테고리 코드")
    private Long categoryCd;
    @Schema(description = "카테고리 명")
    private String categoryName;
    @Schema(description = "카테고리 설명")
    private String categoryDescription;

    @Builder
    public ResponseBasicPilsaCategoryDto(Long categoryCd, String categoryName, String categoryDescription) {
        this.categoryCd = categoryCd;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public static ResponseBasicPilsaCategoryDto from(PilsaCategory category) {
        return ResponseBasicPilsaCategoryDto.builder()
                .categoryCd(category.getCategoryCd())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getDescription())
                .build();
    }

}
