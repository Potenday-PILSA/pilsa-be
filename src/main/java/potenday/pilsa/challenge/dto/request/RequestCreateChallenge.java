package potenday.pilsa.challenge.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    @Schema(description = "카테고리 번호 리스트")
    private List<Long> categories;

    @Builder
    public RequestCreateChallenge(LocalDate startDate, LocalDate endDate, String title, String description, List<Long> categories) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        this.categories = categories;
    }
}
