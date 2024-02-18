package potenday.pilsa.like.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.pilsa.domain.Pilsa;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilsa_id")
    private Pilsa pilsa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private LocalDateTime registDate;

    public Like(Pilsa pilsa, Member member) {
        this.pilsa = pilsa;
        this.member = member;
        this.registDate = LocalDateTime.now();
    }

}
