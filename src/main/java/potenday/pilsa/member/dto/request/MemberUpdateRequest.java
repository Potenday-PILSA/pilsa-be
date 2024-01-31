package potenday.pilsa.member.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequest {

    @Size(max = 50, message = "소개글은 최대 50자로 작성할 수 있습니다.")
    private String description;

    @Builder
    public MemberUpdateRequest(String description) {
        this.description = description;
    }
}
