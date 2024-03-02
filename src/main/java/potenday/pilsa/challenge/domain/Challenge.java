package potenday.pilsa.challenge.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.member.domain.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public Challenge(Member member, LocalDateTime startDate, LocalDateTime endDate, String title, String description) {
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registDate = LocalDateTime.now();
        this.title = title;
        this.description = description;
        this.status = setStatue(startDate, endDate);
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
}
