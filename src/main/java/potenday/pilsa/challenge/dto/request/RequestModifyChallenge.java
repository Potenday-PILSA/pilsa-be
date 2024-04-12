package potenday.pilsa.challenge.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestModifyChallenge {
    @Schema(description = "변경할 제목")
    private String title;
    @Schema(description = "변경할 설명/다짐")
    private String description;

    @Builder
    public RequestModifyChallenge(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
