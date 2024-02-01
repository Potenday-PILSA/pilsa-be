package potenday.pilsa.pilsaImage.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsaImage.domain.YN;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequest {
    @Schema(description = "이미지 url")
    private String imageUrl;
    @Schema(description = "대표 이미지 여부(이미지 여러개중 한개만 Y여아함)")
    private YN thumbnail;
    @Schema(description = "이미지 순서")
    private Integer imageSeq;
}
