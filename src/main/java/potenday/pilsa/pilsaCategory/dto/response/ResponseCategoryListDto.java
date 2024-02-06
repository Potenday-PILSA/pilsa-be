package potenday.pilsa.pilsaCategory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseCategoryListDto {

    @Schema(description = "카테고리 리스트")
    private List<ResponseCategoryDto> categories;

        @Builder
        public ResponseCategoryListDto(List<ResponseCategoryDto> categories) {
                this.categories = categories;
        }

        public static ResponseCategoryListDto from(List<PilsaCategory> pilsaCategoryList) {
                List<ResponseCategoryDto> categoryList = pilsaCategoryList.stream()
                        .map(ResponseCategoryDto::from).collect(Collectors.toList());

                return ResponseCategoryListDto.builder()
                        .categories(categoryList)
                        .build();
        }
}