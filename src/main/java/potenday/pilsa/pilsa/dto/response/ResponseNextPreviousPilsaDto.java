package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsa.domain.Pilsa;

@Getter
@NoArgsConstructor
public class ResponseNextPreviousPilsaDto {
    @Schema(description = "필사 ID")
    private Long pilsaId;
    @Schema(description = "필사 타이틀")
    private String title;

    @Builder
    public ResponseNextPreviousPilsaDto(Long pilsaId, String title) {
        this.pilsaId = pilsaId;
        this.title = title;
    }

    public static ResponseNextPreviousPilsaDto from(Pilsa pilsa) {
        return ResponseNextPreviousPilsaDto.builder()
                .pilsaId(pilsa.getPilsaId())
                .title(pilsa.getTitle())
                .build();
    }
}
