package potenday.pilsa.challenge.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestChallengePilsa {
    @NotNull(message = "챌린지 번호는 빈값일 수 없습니다.")
    private Long challengeId;
    private LocalDate localDate;

    @Builder
    public RequestChallengePilsa(Long challengeId, LocalDate localDate) {
        this.challengeId = challengeId;
        this.localDate = localDate;
    }
}
