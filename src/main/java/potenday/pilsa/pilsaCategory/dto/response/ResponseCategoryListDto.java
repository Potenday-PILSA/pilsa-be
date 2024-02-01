package potenday.pilsa.pilsaCategory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import potenday.pilsa.pilsa.dto.request.RequestPilsaInfoDto;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCategoryListDto {

    @Schema(description = "카테고리 리스트")
    private List<Category> categories;

    @Getter
    @Setter
    @ToString
    private class Category {

        @Schema(description = "카테고리 코드")
        private Long categoryCd;
        @Schema(description = "카테고리 명")
        private String categoryName;
        @Schema(description = "카테고리 설명")
        private String description;
    }
}
