package potenday.pilsa.pilsa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.pilsaContents.domain.PilsaContents;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pilsaInfo")
public class Pilsa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pilsaId;

    // 필사 작성 회원 ID
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", referencedColumnName = "id")
    private Member member;

    // 제목
    @Column
    private String title;

    // 상태값
    @Enumerated(value = EnumType.STRING)
    @Column
    private Status status;

    // 저자
    @Column
    private String author;

    // 출판사
    @Column
    private String publisher;

    // 나만보기 여부
    @Enumerated(value = EnumType.STRING)
    @Column
    private YN privateType;

    // 상위 필사 ID
    @Column
    private Long followPilsaId;

    // 필사 콘텐츠 ID
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pilsaId")
    List<PilsaContents> pilsaContents;

    private LocalDateTime registDate;

    private LocalDateTime updateDate;
}
