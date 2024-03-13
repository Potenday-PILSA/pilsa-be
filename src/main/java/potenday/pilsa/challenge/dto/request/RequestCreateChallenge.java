package potenday.pilsa.challenge.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestCreateChallenge {
    @Schema(description = "시작 날짜")
    private LocalDate startDate;
    @Schema(description = "종료 날짜")
    private LocalDate endDate;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "설명/다짐")
    private String description;

    @Builder
    public RequestCreateChallenge(LocalDate startDate, LocalDate endDate, String title, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }
}
