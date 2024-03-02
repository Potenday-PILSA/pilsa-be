package potenday.pilsa.challenage.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.member.domain.Member;

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
    private String title;

    @Column
    private String description;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Challenge(Member member, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime registDate, LocalDateTime updateDate, String title, String description, Status status) {
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registDate = registDate;
        this.updateDate = updateDate;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
