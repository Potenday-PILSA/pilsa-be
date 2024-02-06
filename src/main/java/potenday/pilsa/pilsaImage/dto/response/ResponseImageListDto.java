package potenday.pilsa.pilsaImage.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsa.dto.request.RequestPilsaInfoDto;
import potenday.pilsa.pilsaImage.domain.PilsaImage;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseImageListDto {

    @Schema(description = "이미지 정보 리스트")
    private List<ResponseImagetDto> images;

    @Builder
    public ResponseImageListDto(List<ResponseImagetDto> images) {
        this.images = images;
    }

    public static ResponseImageListDto from(List<PilsaImage> pilsaImageList) {
        List<ResponseImagetDto> imageList = pilsaImageList.stream()
                .map(ResponseImagetDto::from).collect(Collectors.toList());

        return ResponseImageListDto.builder()
                .images(imageList)
                .build();
    }

}
