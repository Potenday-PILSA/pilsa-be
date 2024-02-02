package potenday.pilsa.pilsaCategory.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsaCategory.domain.YN;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestCategoryDto {
    @Schema(description = "카테고리 명")
    private String categoryName;
    @Schema(description = "카테고리 설명")
    private String description;
    @Schema(description = "사용 여부 [Y: 사용, N: 사용안함]")
    private YN useYn;

    public RequestCategoryDto(String categoryName, String description, YN useYn) {
        this.categoryName = categoryName;
        this.description = description;
        this.useYn = useYn;
    }
}
