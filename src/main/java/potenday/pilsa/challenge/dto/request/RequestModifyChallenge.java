package potenday.pilsa.challenge.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestModifyChallenge {
    @Schema(description = "변경할 제목")
    @Size(max = 20, message = "제목은 최대 20자 까지 입력 가능합니다.")
    private String title;
    @Schema(description = "변경할 설명/다짐")
    @Size(max = 50, message = "다짐은 최대 50자 까지 입력 가능합니다.")
    private String description;

    @Builder
    public RequestModifyChallenge(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
