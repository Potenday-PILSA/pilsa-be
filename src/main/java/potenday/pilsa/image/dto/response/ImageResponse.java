package potenday.pilsa.image.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageResponse {
    @Schema(description = "파일 이름")
    private String fileName;
    @Schema(description = "이미지 파일 URL")
    private String imageUrl;

    @Builder
    public ImageResponse(String fileName, String imageUrl) {
        this.fileName = fileName;
        this.imageUrl = imageUrl;
    }
}
