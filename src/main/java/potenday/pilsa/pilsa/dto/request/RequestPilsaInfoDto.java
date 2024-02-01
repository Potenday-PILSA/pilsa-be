package potenday.pilsa.pilsa.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsaImage.dto.request.ImageRequest;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestPilsaInfoDto {
    @Schema(description = "제목")
    private String title;
    @Schema(description = "저자")
    private String author;
    @Schema(description = "출판사")
    private String publisher;
    // TODO : 아직 기능이 안나왔음.
//    @Schema(description = "나만보기 여부")
//    private YN privateType;
//    @Schema(description = "상위 필사 ID")
//    private Long followPilsaId;
    @Schema(description = "콘텐츠 문구")
    private String textContents;
    @Schema(description = "배경 url")
    private String backgroundImageUrl;
    @Schema(description = "배경 색상")
    private String backgroundColor;
    @Schema(description = "필사 카테고리 코드 리스트")
    private List<Long> categoryCd;
    @Schema(description = "이미지 정보 리스트")
    private List<ImageRequest> images;

}
