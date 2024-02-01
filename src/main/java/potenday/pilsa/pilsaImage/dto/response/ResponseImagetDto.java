package potenday.pilsa.pilsaImage.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import potenday.pilsa.pilsaImage.domain.PilsaImage;
import potenday.pilsa.pilsaImage.domain.YN;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseImagetDto {

    @Schema(description = "이미지 url")
    private String imageUrl;
    @Schema(description = "대표 이미지 여부(이미지 여러개중 한개만 Y여아함)")
    private YN thumbnail;
    @Schema(description = "이미지 순서")
    private Integer imageSeq;

    @Builder
    public ResponseImagetDto(String imageUrl, YN thumbnail, Integer imageSeq) {
        this.imageUrl = imageUrl;
        this.thumbnail = thumbnail;
        this.imageSeq = imageSeq;
    }

    public static ResponseImagetDto from(PilsaImage pilsaImage) {
        return ResponseImagetDto.builder()
                .imageUrl(pilsaImage.getImageUrl())
                .thumbnail(pilsaImage.getThumbnail())
                .imageSeq(pilsaImage.getImageSeq())
                .build();
    }
}
