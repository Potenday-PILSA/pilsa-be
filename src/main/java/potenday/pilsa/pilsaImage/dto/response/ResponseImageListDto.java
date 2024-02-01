package potenday.pilsa.pilsaImage.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsa.dto.request.RequestPilsaInfoDto;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseImageListDto {

    @Schema(description = "이미지 정보 리스트")
    private List<Images> images;

    @Getter
    @Setter
    @ToString
    private class Images {

        @Schema(description = "이미지 url")
        private String imageUrl;
        @Schema(description = "대표 이미지 여부(이미지 여러개중 한개만 Y여아함)")
        private YN thumbnail;
        @Schema(description = "이미지 순서")
        private Integer imageSeq;

    }
}
