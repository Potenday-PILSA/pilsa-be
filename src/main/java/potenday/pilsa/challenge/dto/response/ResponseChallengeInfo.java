package potenday.pilsa.challenge.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.challenge.domain.Challenge;
import potenday.pilsa.challenge.domain.Status;
import potenday.pilsa.global.util.LocalDateUtil;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsaCategory.dto.response.ResponseCategoryListDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseChallengeInfo {
    @Schema(description = "챌린지 ID")
    private Long id;
    @Schema(description = "챌린지 시작 날짜")
    private LocalDate startDate;
    @Schema(description = "챌린지 종료 날짜")
    private LocalDate endDate;
    @Schema(description = "챌린지 등록 날짜")
    private LocalDateTime registDate;
    @Schema(description = "챌린지 제목")
    private String title;
    @Schema(description = "설명/다짐")
    private String description;
    @Schema(description = "상태 [SUCCESS : 성공, FAIL : 실패, ING : 진행중, EXPECTED : 예정]")
    private Status status;
    @Schema(description = "카테고리 리스트")
    private ResponseCategoryListDto categoryListDto;

    @Schema(description = "달성률")
    private Integer achievementRate;

    @Builder
    public ResponseChallengeInfo(Long id, LocalDate startDate, LocalDate endDate, LocalDateTime registDate, String title, String description, Status status, ResponseCategoryListDto categoryListDto, Integer achievementRate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registDate = registDate;
        this.title = title;
        this.description = description;
        this.status = status;
        this.categoryListDto = categoryListDto;
        this.achievementRate = achievementRate;
    }

    public static ResponseChallengeInfo from(Challenge challenge) {
        return ResponseChallengeInfo.builder()
                .id(challenge.getId())
                .startDate(LocalDateUtil.localDateTimeToDate(challenge.getStartDate()))
                .endDate(LocalDateUtil.localDateTimeToDate(challenge.getEndDate()))
                .registDate(challenge.getRegistDate())
                .title(challenge.getTitle())
                .description(challenge.getDescription())
                .status(challenge.getStatus())
                .categoryListDto(ResponseCategoryListDto.from(challenge.getCategories()))
                .achievementRate(challenge.calculateChallengeAchievementRate(challenge))
                .build();
    }

    public static List<ResponseChallengeInfo> from(List<Challenge> challenges) {
        return challenges.stream()
                .map(ResponseChallengeInfo::from)
                .collect(Collectors.toList());
    }
}
