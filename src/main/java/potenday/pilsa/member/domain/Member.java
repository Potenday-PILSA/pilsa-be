package potenday.pilsa.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "memberInfo")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column
    private SocialType socialType;

    @Column
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Status status;

    private String memberKey;

    private String descript;

    private String profileImageUrl;

    private String profileNickName;

    private LocalDateTime registDate;

    private LocalDateTime updateDate;
}
