package potenday.pilsa.pilsa.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class RequestGetPilsa {
    @Schema(description = "나의 필사 조회 할 시 true : 나의 필사 보기 리스트 -> 필사 상세 요청 시에 해당 값은 true, 아니라면 false 를 보내주세요")
    @NotNull
    private Boolean getMyPilsa;

    @Builder
    public RequestGetPilsa(Boolean getMyPilsa) {
        this.getMyPilsa = getMyPilsa;
    }
}
