package potenday.pilsa.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import potenday.pilsa.challenge.domain.Challenge;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseChallengeList {

    private Long totalCount;
    private List<ResponseChallengeInfo> challengeInfos;

    @Builder
    public ResponseChallengeList(Long totalCount, List<ResponseChallengeInfo> challengeInfos) {
        this.totalCount = totalCount;
        this.challengeInfos = challengeInfos;
    }

    public static ResponseChallengeList from(Page<Challenge> challenges) {
        Long totalCount = challenges.getTotalElements();
        List<ResponseChallengeInfo> challengeInfos = challenges.getContent().stream()
                .map(ResponseChallengeInfo::from)
                .toList();

        return ResponseChallengeList.builder()
                .totalCount(totalCount)
                .challengeInfos(challengeInfos)
                .build();
    }
}
