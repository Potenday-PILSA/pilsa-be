package potenday.pilsa.challenge.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.challenge.dto.request.RequestModifyChallenge;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.global.util.LocalDateUtil;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "challenge")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime endDate;

    @Column
    private LocalDateTime registDate;

    @Column
    private LocalDateTime updateDate;

    @Column
    private LocalDateTime deleteDate;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "challenge_categories",
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            joinColumns = @JoinColumn(name = "challenge_id"))
    private List<PilsaCategory> categories;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL)
    private List<Pilsa> pilsas;

    public Challenge(Member member, LocalDateTime startDate, LocalDateTime endDate, String title, String description, List<PilsaCategory> categories) {
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registDate = LocalDateTime.now();
        this.title = title;
        this.description = description;
        this.status = setStatue(startDate, endDate);
        this.categories = categories;
    }

    public Status setStatue(LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(startDate) && now.isBefore(endDate)) {
            return Status.ING;
        } else if (now.isBefore(startDate)) {
            return Status.EXPECTED;
        } else {
            throw new BadRequestException(ExceptionCode.FAIL_DATE_CHALLENGE);
        }
    }

    public void deleteChallenge() {
        this.deleteDate = LocalDateTime.now();
    }

    public void setStatus() {
        this.status = setStatue(this.startDate, this.endDate);
    }

    public void changeStatueSuccessOrFail(Long pilsaCount) {
        long daysBetween = calculateDaysBetween(LocalDateUtil.localDateTimeToDate(this.startDate), LocalDateUtil.localDateTimeToDate(this.endDate));

        if (daysBetween == pilsaCount) {
            this.status = Status.SUCCESS;
        } else {
            this.status = Status.FAIL;
        }
    }

    public static long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    public Integer calculateChallengeAchievementRate(Challenge challenge) {
        List<Pilsa> pilsas = challenge.getPilsas();

        // 삭제된 필사 제외
        removeDeletedPilsas(pilsas);

        // 필사 등록 날짜를 기준으로 중복 제거 후 개수 구하기
        long pilsaCount = countDistinctRegistrationDates(pilsas);

        return challenge.calculateAchievementRateBasedOnPilsa(pilsaCount);
    }

    public int calculateAchievementRateBasedOnPilsa(Long pilsaCount) {
        long daysBetween = calculateDaysBetween(LocalDateUtil.localDateTimeToDate(this.startDate), LocalDateUtil.localDateTimeToDate(this.endDate));

        double rate = (double) pilsaCount / daysBetween * 100;
        return (int) Math.round(rate);
    }

    private static void removeDeletedPilsas(List<Pilsa> pilsas) {
        pilsas.removeIf(pilsa -> pilsa.getDeleteDate() != null);
    }

    public static long countDistinctRegistrationDates(List<Pilsa> pilsas) {
        return pilsas.stream()
                .map(pilsa -> pilsa.getRegistDate().toLocalDate())
                .distinct()
                .count();
    }

    public void modifyChallenge(RequestModifyChallenge request) {
        modifyTitle(request.getTitle());
        modifyDescription(request.getDescription());
    }

    private void modifyTitle(String title) {
        if (title != null && !title.isBlank()) this.title = title;
    }

    private void modifyDescription(String description) {
        if (description != null && !description.isBlank()) this.description = description;
    }

}
