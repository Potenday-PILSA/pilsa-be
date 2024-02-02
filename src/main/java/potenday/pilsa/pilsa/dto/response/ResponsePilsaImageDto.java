package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import potenday.pilsa.pilsaImage.domain.YN;
import potenday.pilsa.pilsaImage.domain.PilsaImage;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ResponsePilsaImageDto {

        @Schema(description = "이미지 ID")
        private Long imageId;
        @Schema(description = "이미지 url")
        private String imageUrl;
        @Schema(description = "대표이미지 여부")
        private YN thumbnail;
        @Schema(description = "이미지 순서")
        private Integer imageSeq;

    @Builder
    public ResponsePilsaImageDto(Long imageId, String imageUrl, YN thumbnail, Integer imageSeq) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.thumbnail = thumbnail;
        this.imageSeq = imageSeq;
    }

    public static ResponsePilsaImageDto from(PilsaImage pilsaImage) {
        return ResponsePilsaImageDto.builder()
                .imageId(pilsaImage.getImageId())
                .imageUrl(pilsaImage.getImageUrl())
                .thumbnail(pilsaImage.getThumbnail())
                .imageSeq(pilsaImage.getImageSeq())
                .build();
    }

}
