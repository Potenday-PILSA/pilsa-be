package potenday.pilsa.challenge.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsaImage.dto.response.ResponseImageListDto;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseChallengePilsaInfo {
    @Schema(description = "필사 아이디")
    private Long id;
    @Schema(description = "필사 제목")
    private String title;
    @Schema(description = "필사 콘텐츠 문구")
    private String textContents;
    @Schema(description = "필사 등록 날짜")
    private LocalDateTime registDate;
    @Schema(description = "이미지 리스트")
    private ResponseImageListDto pilsaImages;

    @Builder
    public ResponseChallengePilsaInfo(Long id, String title, String textContents, LocalDateTime registDate, ResponseImageListDto pilsaImages) {
        this.id = id;
        this.title = title;
        this.textContents = textContents;
        this.registDate = registDate;
        this.pilsaImages = pilsaImages;
    }

    public static ResponseChallengePilsaInfo from(Pilsa pilsa) {
        return ResponseChallengePilsaInfo.builder()
                .id(pilsa.getPilsaId())
                .title(pilsa.getTitle())
                .textContents(pilsa.getTextContents())
                .registDate(pilsa.getRegistDate())
                .pilsaImages(ResponseImageListDto.from(pilsa.getPilsaImages()))
                .build();
    }
}
